package model;

import java.util.List;

public record CloseSummary(float totalRevenue, int soldQuantity, List<Bid> winningBids) {
}