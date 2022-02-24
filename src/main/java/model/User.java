package model;

public record User(int ID, String userName, String firstName, String lastName, String passWord, String organization,
                   boolean isAdmin) implements Model {
    private static boolean isBlocked;

    public User {
        // data validation

        // initiate isBlock default value
        this.isBlocked = false;
    }

    public void block() {
        this.isBlocked = true;
    }

    public boolean isBlocked() {
        return this.isBlocked;
    }

}
