package com.weareadaptive.auctionhouse.model;

public record Bid(String user, int quantity, double price) {

    @Override
    public String toString() {
        return "Bid{" +
                "user='" + user + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
