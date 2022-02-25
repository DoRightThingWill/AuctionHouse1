package com.weareadaptive.auctionhouse.screen;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Consumer;

public abstract class ScreenTemplate {
    protected PrintStream out = System.out;
    protected Scanner scanner = new Scanner(System.in);
    protected boolean screenClose = false;

    // expose them using a getting method

    protected Option createOption(String description, Consumer<DataContext> action) {
        return new Option(description, action, false);
    }

    protected Option createOption(String description) {
        return new Option(description, c -> {
        }, true);
    }

    protected void printScreen(DataContext dataContext, Option... options) {
        while (!this.screenClose) {
            printOptions(dataContext, options);
            listenInputOption(dataContext, options);
        }
    }

    protected void listenInputOption(DataContext dataContext, Option[] options) { // throw exception here in signature?
        try {
            var inputOption = this.scanner.nextLine();
            var optionNumber = Integer.parseInt(inputOption);

            if (optionNumber <= 0 || optionNumber > options.length) {
                out.println("Invalid option, please enter again");
            } else {
                options[optionNumber - 1].action().accept(dataContext);
                this.screenClose = options[optionNumber - 1].close();
            }
        } catch (InputMismatchException | NumberFormatException e) {
            out.println("Invalid option, please enter again");
        }
    }

    protected void printOptions(DataContext dataContext, Option... options) {
        out.println("===================================");
        for (int index = 0; index < options.length; index++) {
            out.printf("%d : %s%n", index + 1, options[index].description());
        }
        out.println("===================================");
        out.println("Enter your option: ");
    }

    protected void enterToContinue() {
        out.println("Press Enter to continue");
        scanner.nextLine();
    }
}
