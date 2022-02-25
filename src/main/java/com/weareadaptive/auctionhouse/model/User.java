package com.weareadaptive.auctionhouse.model;


import static com.weareadaptive.auctionhouse.model.StringNullCheck.isNullOrEmpty;

public record User(int ID, String userName, String firstName, String lastName, String passWord, String organization,
                   boolean isAdmin) implements Model {
    private static boolean isBlocked;

    public User {
        // data validation
        if (ID < 0) {
            throw new MyException("ID can not less than 0");
        }
        if (isNullOrEmpty(userName)) {
            throw new MyException("userName can not be null or empty");
        }
        if (isNullOrEmpty(firstName)) {
            throw new MyException("firstName can not be null or empty");
        }
        if (isNullOrEmpty(lastName)) {
            throw new MyException("lastName can not be null or empty");
        }
        if (isNullOrEmpty(passWord)) {
            throw new MyException("password can not be null or empty");
        }
        if(isNullOrEmpty(organization)){
            throw new MyException("organization can not be null or empty");
        }
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
