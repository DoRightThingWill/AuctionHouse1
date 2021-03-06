package com.weareadaptive.auctionhouse.model;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;

public class UserPool extends ModelPool<User> {


    public void createUser(String userName, String firstName, String lastName, String password, String organization) {
        createUser(userName, firstName, lastName, password, organization, false);
    }

    public void createUser(String userName, String firstName, String lastName, String password, String organization, boolean isAdmin) {
        try{
            userNameExistThenThrow(userName);
            int userID = nextID();
            User newUser;
            if (isAdmin) {
                newUser = new User(userID, userName, firstName, lastName, password, organization, true);
            } else {
                newUser = new User(userID, userName, firstName, lastName, password, organization, false);
            }
            add(userID, newUser);
        } catch (MyException e){
            System.out.println(e.getMessage());
        }

    }


    public Optional<User> findUserByNamePassword(String userName, String password) {
        return stream()
                .filter(user -> user.userName().equals(userName))
                .filter(user -> user.passWord().equals(password))
                .findFirst();
    }

    public List<String> allOrganizations() {
        return stream()
                .filter(user -> !user.isAdmin())
                .map(User::organization)
                .distinct()
                .sorted()
                .toList();
    }

    public List<OrganizationDetail> allOrganizationDetails() {
        return stream()
                .filter(user -> !user.isAdmin())
                .collect(groupingBy(User::organization))
                .entrySet()
                .stream()
                .map(entry -> new OrganizationDetail(entry.getKey(), entry.getValue()))
                .sorted(comparing(OrganizationDetail::name))
                .toList();
    }

    public void userNameExistThenThrow(String userName){
        if(models().values()
                .stream()
                .anyMatch(user -> user.userName().equals(userName))
        ){
            throw new MyException("username already exists");
        }
    }
}
