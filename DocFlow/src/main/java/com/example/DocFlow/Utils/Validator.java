package com.example.DocFlow.Utils;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern NAME = Pattern.compile("[a-zA-Z]+\\.?",Pattern.CASE_INSENSITIVE);

    private static final Pattern EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern MOBILE = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$");

            //Pattern.compile("^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{1,3}\\\\))|\\\\d{1,3})[- .]?\\\\d{3,4}[- .]?\\\\d{4}$");
    public static boolean validateEmail(String email) {
        return EMAIL.matcher(email).matches();
    }
    public static boolean validatePhone(String s) {
        return MOBILE.matcher(s).matches();
    }
    public static boolean validateName(String name){
        return NAME.matcher(name).matches();
    }

}
