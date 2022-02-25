package com.weareadaptive.auctionhouse.screen;

public class UserScreen extends ScreenTemplate {

    private UserManagementScreen userManagementScreen;
    private AuctionManagementScreen auctionManagementScreen;

    public UserScreen() {
        this.userManagementScreen = new UserManagementScreen();
        this.auctionManagementScreen = new AuctionManagementScreen();
    }

    public void printScreen(DataContext dataContext, boolean isAdmin) {
        if (isAdmin) {
            printScreen(dataContext
                    , createOption("User Management", this::showUserManagement)
                    , createOption("Auction Management", this::showAuctionManagement)
                    , createOption("Log out"));
        } else {
            printScreen(dataContext
                    , createOption("Auction Management", this::showAuctionManagement)
                    , createOption("Log out"));
        }
    }

    private void showUserManagement(DataContext dataContext) {
        userManagementScreen.printScreen(dataContext);
    }

    private void showAuctionManagement(DataContext dataContext) {
        auctionManagementScreen.printScreen(dataContext);
    }
}
