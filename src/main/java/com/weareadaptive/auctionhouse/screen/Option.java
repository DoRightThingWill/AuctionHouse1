package com.weareadaptive.auctionhouse.screen;

import java.util.function.Consumer;

public record Option(String description, Consumer<DataContext> action, boolean close) {
}
