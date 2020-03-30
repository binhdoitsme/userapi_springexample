package com.example.userapi.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.example.userapi.domain.dto.UserDto;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserValidation {

    // validation patterns
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{8,63}$");
    private static final Pattern RAW_PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9_!@#$%&]{6,45}$");
    private static final Pattern DISPLAY_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{8,127}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+\\.[a-zA-Z0-9_]+$");

    // private static final String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    // returns a list of invalid fields in input user data
    public static List<String> getInvalidFields(UserDto input) {
        List<String> invalidFields = new ArrayList<>();
        if (!validateUsername(input.getUsername())) {
            invalidFields.add("Username");
        }
        if (!validateEnteredPassword(input.getPassword())) {
            invalidFields.add("Password");
        }
        if (!validateDisplayName(input.getDisplayName())) {
            invalidFields.add("Display name");
        }
        if (!validateEmail(input.getEmail())) {
            invalidFields.add("Email");
        }
        return invalidFields;
    }

    public static boolean validateUserInput(UserDto input) {
        return validateUsername(input.getUsername())
                && validateEnteredPassword(input.getPassword())
                && validateDisplayName(input.getDisplayName())
                && validateEmail(input.getEmail());
    }

    private static boolean validateUsername(String username) {
        if (username == null) return false;
        return USERNAME_PATTERN.matcher(username).matches();
    }

    private static boolean validateEnteredPassword(String password) {
        if (password == null) return false;
        return RAW_PASSWORD_PATTERN.matcher(password).matches();
    }
    
    private static boolean validateDisplayName(String displayName) {
        if (displayName == null) return false;
        return DISPLAY_NAME_PATTERN.matcher(displayName).matches();
    }

    private static boolean validateEmail(String email) {
        if (email == null) return false;
        return email.length() > 0 && email.length() < 1023 && EMAIL_PATTERN.matcher(email).matches();
    }

    public static String encodePassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public static boolean validatePassword(String entered, String expectedEncoded, String generatedSalt) {
        if (entered == null) return false;
        String encodedEntered = encodePassword(entered, generatedSalt);
        return encodedEntered.equals(expectedEncoded);
    }
}
