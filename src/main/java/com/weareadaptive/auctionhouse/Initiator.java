package com.weareadaptive.auctionhouse;

import com.weareadaptive.auctionhouse.model.AuctionPool;
import com.weareadaptive.auctionhouse.model.UserPool;
import com.weareadaptive.auctionhouse.screen.DataContext;

public class Initiator {


    private DataContext dataContext;

    public Initiator() {
        dataContext = new DataContext(new UserPool(), new AuctionPool());
    }

    private void initialize() {
        var userPool = dataContext.userPool();
        userPool.createUser("admin", "adminFName", "adminLName", "adminPWD", "adminOrg", true);
        userPool.createUser("userOne", "userOneFName", "userOneLName", "uOnePWD", "orgOne");
        userPool.createUser("userTwo", "userTwoFName", "userTwoLName", "uTwoPWD", "orgTwo");
    }

    public DataContext getDataContext() {
        initialize();
        return dataContext;
    }

}
