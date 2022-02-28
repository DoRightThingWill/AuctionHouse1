package com.weareadaptive.auctionhouse.model;

import java.util.List;

public class AuctionPool extends ModelPool<Auction> {


    public List<Auction> liveAuctionsOfUser(String userName) {
        return stream()
                .filter(auction -> auction.owner().equals(userName))
                .filter(auction -> auction.status() == Auction.Status.OPEN)
                .sorted()
                .toList();
    }

    public Auction getAuctionByID(int id) {
        return models().values()
                .stream()
                .filter(auction -> auction.ID() == id)
                .findFirst()
                .orElseThrow(() -> new MyException("Can not find auction by this ID!"));
    }

    public List<Auction> getBiddableAuctions(String userName) {
        return models().values().stream()
                .filter(auction -> !auction.owner().equals(userName))
                .filter(auction -> auction.status().equals(Auction.Status.OPEN))
                .toList();
    }

}
