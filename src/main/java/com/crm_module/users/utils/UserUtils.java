package com.crm_module.users.utils;

import com.crm_module.users.User;
import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.function.Function;

@UtilityClass
public class UserUtils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 10;
    private static final SecureRandom RANDOMIZER = new SecureRandom();

    public static String generateUserName(User user) {
        return user.getFirstName() + "." + user.getLastName();
    }

    public static String generateUniqueUsername(
            String baseUsername,
            Function<String, Boolean> usernameExistsChecker
    ) {
        var uniqueUsername = baseUsername;
        var counter = 1;

        while (usernameExistsChecker.apply(uniqueUsername)) {
            uniqueUsername = baseUsername + counter;
            counter++;
        }

        return uniqueUsername;
    }

    public static String generatePassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(CHARACTERS.charAt(RANDOMIZER.nextInt(CHARACTERS.length())));
        }

        return password.toString();
    }
}
