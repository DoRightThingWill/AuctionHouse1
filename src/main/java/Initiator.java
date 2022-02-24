import model.AuctionPool;
import model.UserPool;
import screen.DataContext;

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
