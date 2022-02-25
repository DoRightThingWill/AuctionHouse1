package com.weareadaptive.auctionhouse.screen;

import com.weareadaptive.auctionhouse.model.AuctionPool;
import com.weareadaptive.auctionhouse.model.UserPool;

public record DataContext(UserPool userPool, AuctionPool auctionPool) {
    private static String currentUser;

    public String currentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        DataContext.currentUser = currentUser;
    }
}