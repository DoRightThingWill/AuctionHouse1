package com.weareadaptive.auctionhouse.screen;

import com.weareadaptive.auctionhouse.model.Auction;

public class AuctionManagementScreen extends ScreenTemplate {

    public void printScreen(DataContext dataContext) {
        printScreen(
                dataContext,
                createOption("Create an auction", this::createAuction),
                createOption("See your auctions", this::showAllAuctions),
                createOption("Close an auction", this::closeAuction),
                createOption("Bid", this::bidOnAuction),
                createOption("Won bids", this::showWonBids),
                createOption("Lost bids", this::showLostBids),
                createOption("Go back")
        );
    }

    private void createAuction(DataContext dataContext) {
        try {
            out.println("===>Create auction :");
            out.println("Enter your symbol");
            var symbol = scanner.nextLine();
            out.println("Enter the quantity:");
            var quantity = Parser.parseInt(scanner.nextLine());

            out.println("Enter the min price:");
            var minPrice = Parser.parseFloat(scanner.nextLine());

            int auctionID = dataContext.auctionPool().nextID();
            dataContext.auctionPool()
                    .add(auctionID, new Auction(auctionID, symbol, quantity, minPrice, dataContext.currentUser())
                    );
            out.printf("Auction ID %d is created %n", auctionID);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    private void showAllAuctions(DataContext dataContext) {
        dataContext.auctionPool()
                .stream()
                .filter(auction -> auction.owner().equals(dataContext.currentUser()))
                .forEach(auction -> {
                            out.println("============================");
                            out.printf("ID %d %n", auction.ID());
                            out.printf("Symbol : %s %n", auction.symbol());
                            out.printf("Status : %s %n", auction.status());

                            out.println("All bids:");
                            auction.getBids().forEach(bid -> out.println(bid.toString()));

                            if (auction.status().equals(Auction.Status.CLOSED)) {
                                var closedSummary = auction.closeSummary();
                                out.printf("Total revenue : %s %n", closedSummary.totalRevenue());
                                out.printf("Total sold quantity : %s %n", closedSummary.soldQuantity());
                                out.println("Winning bids: ");
                                closedSummary.winningBids()
                                        .forEach(bid -> {
                                            out.printf("\tUser: %s %n", bid.user());
                                            out.printf("\tSettled quantity: %s %n", bid.quantity());
                                            out.printf("\tPrice: %s %n", bid.price());
                                        });

                            }
                            out.println("============================");
                        }
                );
        enterToContinue();
    }

    private void closeAuction(DataContext dataContext) {
        dataContext.auctionPool()
                .liveAuctionsOfUser(dataContext.currentUser())
                .forEach(auction -> out.printf("Auction ID : %d | Symbol : %s %n", auction.ID(), auction.symbol()));

        try {
            out.println("Enter the auction ID");
            var auctionID = Parser.parseInt(scanner.nextLine());
            dataContext.auctionPool()
                    .getAuctionByID(auctionID)
                    .close();
            out.printf("Auction ID : %s has been closed %n", auctionID);
        } catch (Exception e) {
            out.println(e.getMessage());
        }

    }




    private void bidOnAuction(DataContext dataContext) {
        dataContext.auctionPool().getBiddableAuctions(dataContext.currentUser())
                .stream()
                .forEach(auction -> out.printf("Auction ID : %d || Symbol: %s", auction.ID(), auction.symbol()));

        try {
            out.println("Enter the auction ID");
            var auctionID = Parser.parseInt(scanner.nextLine());
            var auction = dataContext.auctionPool().getAuctionByID(auctionID);
            if (auction == null) {
                out.println("Can not find auction by this ID");
                return;
            }
            out.println("Enter the quantity");
            var quantity = Parser.parseInt(scanner.nextLine());
            out.println("Enter the price");
            var price = Parser.parseFloat(scanner.nextLine());

            auction.bid(dataContext.currentUser(), quantity, price);
            out.println("Bid created!!");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    private void showWonBids(DataContext dataContext) {
        dataContext.auctionPool()
                .stream()
                .filter(auction -> auction.status().equals(Auction.Status.CLOSED))
                .forEach(auction ->
                        auction.closeSummary()
                                .winningBids()
                                .stream()
                                .filter(bid -> bid.user().equals(dataContext.currentUser()))
                                .forEach(bid -> out.printf("Auction ID : %s, symbol : %s, quantity: %s, settled-quantity: %s, price : %s\n",
                                        auction.ID(),
                                        auction.symbol(),
                                        auction.quantity(),
                                        bid.quantity(),
                                        bid.price())));
    }

    private void showLostBids(DataContext dataContext) {
        dataContext.auctionPool()
                .stream()
                .filter(auction -> auction.status().equals(Auction.Status.CLOSED))
                .forEach(auction -> {
                    boolean userDidNotWinAuction = auction
                            .closeSummary()
                            .winningBids()
                            .stream()
                            .noneMatch(bid -> bid.user().equals(dataContext.currentUser()));
                    if (userDidNotWinAuction) {
                        auction.getBids()
                                .stream()
                                .filter(bid -> bid.user().equals(dataContext.currentUser()))
                                .forEach(bid ->
                                        out.printf("Auction ID: %s, Symbol : %s, Quantity : %s, Price : %s %n",
                                                auction.ID(),
                                                auction.symbol(),
                                                bid.quantity(),
                                                bid.price()));
                    }
                });

    }

}
