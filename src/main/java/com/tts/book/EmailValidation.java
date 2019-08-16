package com.tts.book;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

    public static boolean email_Valid(String email) {
        boolean status = false;
        String email_pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z-]+" +
                "(\\.A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

           /* String email_pattern="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+" +
                    "(\\.A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";*/

        Pattern pattern = Pattern.compile(email_pattern);
        Matcher mather = pattern.matcher(email);
        if (mather.matches()) {
            status = true;
        } else {
            status = false;

        }
        return status;
    }
}


