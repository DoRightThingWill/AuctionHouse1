package com.weareadaptive.auctionhouse.model;

import java.math.BigDecimal;
import java.util.List;

public record CloseSummary(BigDecimal totalRevenue, int soldQuantity, List<Bid> winningBids) {
}