package screen;


public class LoginScreen extends ScreenTemplate {
    private final DataContext dataContext;
    private final UserScreen userScreen; // only the name immutable

//    private final String input;

    public LoginScreen(DataContext inputDataContext) {
        this.dataContext = inputDataContext;
        this.userScreen = new UserScreen();
    }

    public void start() {
        printScreen(dataContext, createOption("Login", this::login), createOption("Quit"));
    }

    private void login(DataContext dataContext) {
        out.println("Enter your owner :");
        var userName = scanner.nextLine();
        out.println("Enter your password :");
        var password = scanner.nextLine();

        dataContext.userPool().findUserByName(userName, password).ifPresentOrElse(user -> {
            dataContext.setCurrentUser(userName);
            userScreen.printScreen(dataContext, user.isAdmin());
        }, () -> out.println("Username and password does not match our record"));
    }
}
