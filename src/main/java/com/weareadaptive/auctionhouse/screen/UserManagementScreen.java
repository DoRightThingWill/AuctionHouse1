package com.weareadaptive.auctionhouse.screen;

public class UserManagementScreen extends ScreenTemplate {
    public void printScreen(DataContext dataContext) {
        printScreen(dataContext
                , createOption("Create User", this::createUser)
                , createOption("Show Users", this::showUsers)
                , createOption("Show organizations", this::showOrganizations)
                , createOption("Show Organizations Details", this::showOrganizationDetail)
                , createOption("Go back"));
    }

    private void showOrganizations(DataContext dataContext) {
        out.println("==== All organizations");
        dataContext.userPool().allOrganizations().forEach(organization -> {
            out.println(organization);
        });
        enterToContinue();
    }

    private void showOrganizationDetail(DataContext dataContext) {
        out.println("==== All organizations");
        dataContext.userPool().allOrganizationDetails().forEach(orgInfo -> {
            out.println(orgInfo.name());
            orgInfo.users().forEach(user -> {
                out.printf("\t UserName: %s%n", user.userName());
            });
        });
        enterToContinue();
    }

    private void showUsers(DataContext dataContext) {
        out.println("===All users: ");
        dataContext.userPool().stream().forEach(user -> {
            out.printf("ID %d, UserName: %s, First Name: %s, Last Name: %s, Organization: $s%n"
                    , user.ID()
                    , user.userName()
                    , user.firstName()
                    , user.lastName()
                    , user.organization());
        });
        enterToContinue();
    }

    private void createUser(DataContext dataContext) {
        try {
            out.println("===> Create User");
            String userName;
            out.println("Enter the owner");
            var newUserName = scanner.nextLine();

            out.println("Enter the password");
            var password1 = scanner.nextLine();

            out.println("Enter the password again");
            var password2 = scanner.nextLine();

            if (!password1.equals(password2)) {
                out.println("Password does not match");
                return;
            }

            out.println("Enter the first name");
            var firstName = scanner.nextLine();

            out.println("Enter the last name");
            var lastName = scanner.nextLine();

            out.println("Enter the organization");
            var organization = scanner.nextLine();

            dataContext.userPool().createUser(newUserName, firstName, lastName, password1, organization);

            out.printf("User %d has been successfully created", dataContext.userPool().nextID());
            enterToContinue();
        } catch (RuntimeException e) {
            out.printf("Can not create the user, because %", e.getMessage());
        }
    }
}
