package com.crm_module.utils;

import com.crm_module.models.users.User;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;

import java.util.function.Function;

@UtilityClass
@Slf4j
public class UserUtils {
    private static final String USERNAME_SEPARATOR = ".";

    public static String generateUniqueUsername(
            User user,
            Function<String, Boolean> usernameExistsChecker
    ) {
        log.debug("Stated creating unique username... ");

        var baseUsername = user.getFirstName() + USERNAME_SEPARATOR + user.getLastName();
        var uniqueUsername = baseUsername;
        var counter = 1;
        log.debug("Base username was created... ");

        while (usernameExistsChecker.apply(uniqueUsername)) {
            uniqueUsername = baseUsername + counter;
            counter++;
        }

        log.info("Unique username was created-" + uniqueUsername);
        return uniqueUsername;
    }

    public static String generatePassword() {
        log.debug("Stated generating password... ");
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .build();

        log.info("Password was successfully generated... ");
        return generator.generate(10);
    }
}
