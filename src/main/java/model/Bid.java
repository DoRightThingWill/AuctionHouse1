package model;

public record Bid(String user, int quantity, float price) {

    @Override
    public String toString() {
        return "Bid{" +
                "user='" + user + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
