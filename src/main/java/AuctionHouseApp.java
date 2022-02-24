import screen.LoginScreen;

public class AuctionHouseApp {

    public static void main(String[] args) {
        Initiator initiator = new Initiator();
        var dataContext = initiator.getDataContext();

        LoginScreen loginScreen = new LoginScreen(dataContext);
        loginScreen.start();
    }
}
