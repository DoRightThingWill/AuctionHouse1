package com.weareadaptive.auctionhouse.model;


import java.util.List;

public record OrganizationDetail(String name, List<User> users) {
}