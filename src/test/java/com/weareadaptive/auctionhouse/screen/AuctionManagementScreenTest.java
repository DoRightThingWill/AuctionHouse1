package com.weareadaptive.auctionhouse.screen;

import com.weareadaptive.auctionhouse.TestData;
import com.weareadaptive.auctionhouse.model.AuctionPool;
import com.weareadaptive.auctionhouse.model.UserPool;
import org.junit.jupiter.api.BeforeEach;

class AuctionManagementScreenTest {

    private TestData testData;
    private DataContext dataContext;

    @BeforeEach
    void initialize(){
        this.testData = new TestData();
        this.dataContext = new DataContext(new UserPool(), new AuctionPool());
    }




}