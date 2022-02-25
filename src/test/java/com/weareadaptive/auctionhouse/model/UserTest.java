package com.weareadaptive.auctionhouse.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User testUser;

    @BeforeEach
    void initialize() {
        this.testUser = new User(0, "user1", "userf", "user1l", "user1", "org1", true);
    }

    @DisplayName("check if user is blocked")
    @Test
    void returnFalseWhenNotBlocked() {
        // arrange
        // act
        // assert
        assertFalse(testUser.isBlocked());
    }

    @DisplayName("block a user")
    @Test
    void returnTrueWhenBlockUser() {
        // arrange
        // act
        this.testUser.block();
        // assert
        assertTrue(this.testUser.isBlocked());
    }

    @ParameterizedTest(name = "{0} is null or empty")
    @MethodSource("userCreationArguments")
    void throwExceptionWhenNullOrEmptyInput(String argumentName, Executable userExecutable) {
        // arrange & act
        var exception = assertThrows(MyException.class, userExecutable);
        // assert
        assertTrue(exception.getMessage().contains(argumentName));
    }

    static Stream<Arguments> userCreationArguments() {
        return Stream.of(
                Arguments.of("ID", (Executable) () -> new User(-1, "t", "t", "t", "t", "t", true)),
                Arguments.of("userName", (Executable) () -> new User(1, "", "t", "t", "t", "t", true)),
                Arguments.of("firstName", (Executable) () -> new User(1, "t", "", "t", "t", "t", true)),
                Arguments.of("lastName", (Executable) () -> new User(1, "t", "t", "", "t", "t", true)),
                Arguments.of("password", (Executable) () -> new User(1, "t", "t", "t", "", "t", true)),
                Arguments.of("organization", (Executable) () -> new User(1, "t", "t", "t", "t", "", true)));
    }

}