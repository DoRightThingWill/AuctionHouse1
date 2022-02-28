package com.weareadaptive.auctionhouse.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPoolTest {

    private UserPool testUsersPool;

    @BeforeEach
    void initialize() {
        this.testUsersPool = new UserPool();
        testUsersPool.createUser("admin", "adminf", "adminl", "admin", "org1", true);
        testUsersPool.createUser("user1", "user1", "user1", "user1", "org2", false);
        testUsersPool.createUser("user2", "user2", "user2", "user2", "org3", false);
        testUsersPool.createUser("user3", "user3", "user3", "user3", "org1", false);
    }


    @Test
    void returnUserWhenCrateUser() {
        // arrange
        testUsersPool.createUser("user4", "user4", "user4", "user4PWD", "org1");
        testUsersPool.createUser("admin","a","a", "a", "a", true);
        // act
        User actual = testUsersPool.models().get(5);
        User expected = new User(5, "user4", "user4", "user4", "user4PWD", "org1", false);
        // assert
        assertAll(
                () -> assertEquals(actual.ID(), expected.ID()),
                () -> assertEquals(actual.userName(), expected.userName()),
                () -> assertEquals(actual.firstName(), expected.firstName()),
                () -> assertEquals(actual.lastName(), expected.lastName()),
                () -> assertEquals(actual.passWord(), expected.passWord()),
                () -> assertEquals(actual.organization(), expected.organization()),
                () -> assertEquals(actual.isAdmin(), expected.isAdmin())
        );
    }


    @DisplayName("can not find user if username password do not match")
    @Test
    void returnFalseWhenUsernamePasswordNotMatch() {
        // arrange
        // act
        var actual = testUsersPool.findUserByNamePassword("user1", "abc");
        // assert
        assertFalse(actual.isPresent());
    }

    @DisplayName("able to find the user if username password match")
    @Test
    void returnTrueWhenUsernamePasswordMatch(){
        // arrange
        // act
        var actual = testUsersPool.findUserByNamePassword("user1", "user1");
        // assert
        assertTrue(actual.isPresent());
    }

    @DisplayName("can not find user when username not exist")
    @Test
    void returnFalseWhenUsernameNotExist(){
        // arrange
        // act
        var actual = testUsersPool.findUserByNamePassword("user200", "user1");
        // assert
        assertFalse(actual.isPresent());
    }

    @DisplayName("test returned quantity of organizations")
    @Test
    void returnOrganizationQuantity() {
        var organizationQuantity = testUsersPool.allOrganizations().size();
        assertEquals(3, organizationQuantity);
    }

    @Test
    void allOrganizationDetails() {
    }

    @DisplayName("show organization details")
    @Test
    void returnDetailedOrganizations(){
        var organizationWithDetails = testUsersPool.allOrganizationDetails();
        organizationWithDetails.forEach(organizationDetail ->
                assertEquals(1, organizationDetail.users().size()));
    }
}