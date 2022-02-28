package com.weareadaptive.auctionhouse.model;

import com.weareadaptive.auctionhouse.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AuctionPoolTest {

    private AuctionPool auctionPool;
    private TestData testData;

    @BeforeEach
    void initialize(){
        this.auctionPool = new AuctionPool();
        this.testData = new TestData();
    }

    @ParameterizedTest
    @DisplayName("return open auctions belonging to current user")
    @MethodSource("createUsers")
    void getGetLiveAuctionsOfUser(String user){
        // arrange
        this.auctionPool.add(1, this.testData.auc1);
        this.auctionPool.add(2, this.testData.auc2);
        this.auctionPool.add(3, this.testData.auc3);

        // act
        var expectedAuctions = this.auctionPool.liveAuctionsOfUser(user);
        // assert

        assertTrue(
                expectedAuctions.stream()
                        .allMatch(auction -> auction.owner().equals(user)));
    }

    static Stream<String> createUsers(){
      return Stream.of(
              TestData.auc1.owner(),
              TestData.auc2.owner(),
              TestData.auc3.owner()
      );
    }


    @ParameterizedTest
    @DisplayName("return open auctions not belonging to current user")
    @ValueSource(strings = {"user1", "user2", "user3", "user4"})
    void testGetBiddableAuctions(String user){
        // arrange
        this.auctionPool.add(1, this.testData .auc1);
        this.auctionPool.add(2, this.testData .auc2);
        this.auctionPool.add(3, this.testData .auc3);

        // act
        var expectedAuctions = this.auctionPool.getBiddableAuctions(user);
        // assert
        assertTrue(
                expectedAuctions.stream()
                        .noneMatch(auction -> auction.owner().equals(user)));
    }

    @DisplayName("return auction found by ID")
    @Test
    void returnAuctionFoundByID(){
        // arrange
        this.auctionPool.add(1, this.testData.auc1);
        this.auctionPool.add(2, this.testData.auc2);
        this.auctionPool.add(3, this.testData.auc3);
        // act
        var expectedAuction = auctionPool.getAuctionByID(1);
        // assert
        assertAll(
                ()->assertEquals(1, expectedAuction.ID()),
                ()->assertThrows(MyException.class,()->auctionPool.getAuctionByID(4))
        );
    }
}