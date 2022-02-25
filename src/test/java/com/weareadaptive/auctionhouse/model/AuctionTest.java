package com.weareadaptive.auctionhouse.model;

import com.weareadaptive.auctionhouse.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AuctionTest {
    private TestData testData;

    @BeforeEach
    void initialize() {
        this.testData = new TestData();
    }

    // bids is empty
    // bids is not empty
    // auction qty > bids-total-quantity
    // auction qty < bids-total-quantity
    @DisplayName("test when the bids-list is empty")
    @Test
    void returnEmptyWhenNoBidsInAuction() {
        // arrange
        var auction = testData.auc1;
        // act
        auction.close();
        var bidsList = auction.getBids();
        var closeSummary = auction.closeSummary();
        // assert
        assertAll(
                () -> assertTrue(closeSummary.winningBids().isEmpty()),
                () -> assertTrue(closeSummary.soldQuantity() == 0),
                () -> assertTrue(closeSummary.totalRevenue().compareTo(BigDecimal.ZERO) == 0)
        );
    }

    @DisplayName("test when auction-quantity > bids-total-quantity")
    @Test
    void testWhenAuctionQuantityLargerThanBidsTotalQuantity() {
        // arrange
        this.testData.auc4.bid("user2", 30, 3.2);
        this.testData.auc4.bid("user3", 10, 1.2);
        this.testData.auc4.bid("user3", 10, 2.1);
        this.testData.auc4.bid("user2", 5, 3.3);

        // act
        this.testData.auc4.close();
        var closeSummary = this.testData.auc4.closeSummary();
        // assert
        assertAll(
                () -> assertTrue(closeSummary.totalRevenue().compareTo(BigDecimal.valueOf(145.5)) == 0),
                () -> assertEquals(closeSummary.soldQuantity(), 55));
    }

    @DisplayName("test when auction-quantity > bids-total-quantity")
    @Test
    void testWhenAuctionQuantityLessThanBidsTotalQuantity() {
        // arrange
        this.testData.auc4.bid("user2", 30, 3.2);
        this.testData.auc4.bid("user3", 40, 1.2);
        this.testData.auc4.bid("user3", 40, 2.1);
        this.testData.auc4.bid("user2", 5, 3.3);

        // act
        this.testData.auc4.close();
        var closeSummary = this.testData.auc4.closeSummary();
        // assert
        assertAll(
                () -> assertTrue(closeSummary.totalRevenue().compareTo(BigDecimal.valueOf(226.5)) == 0),
                () -> assertEquals(closeSummary.soldQuantity(), 100));
    }


}