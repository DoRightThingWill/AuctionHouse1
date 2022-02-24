package model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

public record Auction(int ID, String symbol, int quantity, float minPrice, String owner) implements Model {

    private static Status status;
    private static List<Bid> bids;
    private static CloseSummary closeSummary;

    public Auction {
        this.status = Status.OPEN;
        this.bids = new ArrayList<>();
    }

    public Status status() {
        return status;
    }

    public void close() {

        this.status = Status.CLOSED;
        var orderedBids = bids.stream().sorted(comparing(Bid::price).reversed()).toList();
        var bidsIterator = orderedBids.listIterator();

        var remainingQuantity = this.quantity;
        List<Bid> winningBids = new ArrayList<>();
        float totalRevenue = 0;
        int soldQuantity = 0;

        while (bidsIterator.hasNext() && remainingQuantity > 0) {
            var currentBid = bidsIterator.next();
            int bidQuantity = Math.min(remainingQuantity, currentBid.quantity());
            remainingQuantity -= bidQuantity;
            totalRevenue += bidQuantity * currentBid.price();
            soldQuantity += bidQuantity;

            winningBids.add(new Bid(currentBid.user(), bidQuantity, currentBid.price()));
        }

        closeSummary = new CloseSummary(totalRevenue, soldQuantity, winningBids);
    }

    public void bid(String bidPerson, int quantity, float price) {
        this.bids.add(new Bid(bidPerson, quantity, price));
    }

    public CloseSummary closeSummary() {
        return this.closeSummary;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public enum Status {
        OPEN, CLOSED
    }

}
