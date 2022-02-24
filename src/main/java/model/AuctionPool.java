package model;

import java.util.HashMap;
import java.util.List;

public class AuctionPool extends ModelPool<Auction> {

    public AuctionPool() {
        models = new HashMap<Integer, Auction>();
        nextID = 1;
    }

    public List<Auction> liveAuctionsOfUser(String userName) {
        return stream()
                .filter(auction -> auction.owner().equals(userName))
                .filter(auction -> auction.status() == Auction.Status.CLOSED)
                .sorted()
                .toList();
    }

    public Auction getAuctionByID(int id) {
        return models.get(id);
    }

}
