package com.weareadaptive.auctionhouse;

import com.weareadaptive.auctionhouse.model.Auction;
import com.weareadaptive.auctionhouse.model.Bid;
import com.weareadaptive.auctionhouse.model.User;

public class TestData {
    public final String ORG_1 = "ORG_1";
    public  final String ORG_2 = "ORG_2";
    public  final String ORG_3 = "ORG_3";

    public  final User ADMIN = new User(1, "admin", "adminf", "adminl", "admin", ORG_1, true);
    public  final User USER1 = new User(2, "user1", "user1", "user1", "user1", ORG_2, false);
    public  final User USER2 = new User(3, "user2", "user2", "user2", "user2", ORG_3, false);
    public  final User USER3 = new User(4, "user3", "user3", "user3", "user3", ORG_1, false);

    public static final String GOOGLE = "GOOGLE";
    public static final String TESLA = "TESLA";
    public static final String ADAPT = "ADAPT";

    public static final Auction auc1 = new Auction(1, GOOGLE, 100, 2.2, "user1");
    public static final Auction auc2 = new Auction(2, TESLA, 100, 2.3, "user2");
    public static final Auction auc3 = new Auction(3, ADAPT, 100, 1.2, "user3");
    public  final Auction auc4 = new Auction(3, ADAPT, 100, 1.2, "user3");

    public  final Bid bid1_1 = new Bid("user2", 30, 3.2);
    public  final Bid bid1_2 = new Bid("user2", 10, 2.4);
    public  final Bid bid1_3 = new Bid("user3", 20, 2.2);
    public  final Bid bid1_4 = new Bid("user2", 15, 3.3);
    public  final Bid bid1_5 = new Bid("user2", 10, 1.1);
    public  final Bid bid1_6 = new Bid("user3", 30, 2.2);

    public TestData(){}
}
