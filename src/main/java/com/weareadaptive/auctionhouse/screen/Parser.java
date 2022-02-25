package com.weareadaptive.auctionhouse.screen;

public class Parser {

    public static int parseInt(String input) throws ParsingException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ParsingException(input, "int");
        }
    }

    public static float parseFloat(String input) throws ParsingException {
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new ParsingException(input, "float");
        }
    }

    protected static class ParsingException extends Exception {
        public ParsingException(String input, String type) {
            super(String.format("%s is not the type of $s$n", input, type));
        }
    }
}
