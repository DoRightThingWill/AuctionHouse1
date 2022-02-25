package com.weareadaptive.auctionhouse.model;

public class StringNullCheck {
    public StringNullCheck() {
    }

    public static boolean isNullOrEmpty(String str){
        return str.isEmpty() || str.isBlank();
    }
}
