package screen;

import model.AuctionPool;
import model.UserPool;

public record DataContext(UserPool userPool, AuctionPool auctionPool) {
    private static String currentUser;

    public String currentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        DataContext.currentUser = currentUser;
    }
}