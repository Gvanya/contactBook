package com.softindustry.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerValidation {
    private Pattern pattern;
    private Matcher matcher;

    private static final String LastNameValidator = "^[A-Za-z]{5,20}$";
    private static final String FirstNameValidator = "^[A-Za-z]{2,20}$";
    private static final String AgeValidator = "^[0-9]{0,3}$";
    private static final String GenderValidator = "^[A-Za-z]{1,8}$";
    private static final String PhoneNumberValidator = "^\\+\\(\\d{3}\\)\\ \\d{3}\\-\\d{2}\\-\\d{2}$";

    public ServerValidation(){
    }

    public boolean lastNameValidate(final String text){
        pattern = Pattern.compile(LastNameValidator);
        if( text == null || text.isEmpty() ){
            return false;}
        matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public boolean firstNameValidate(final String text){
        pattern = Pattern.compile(FirstNameValidator);
        if( text == null || text.isEmpty() )
            return false;
        matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public boolean ageValidate(final String text){
        pattern = Pattern.compile(AgeValidator);
        if( text == null || text.isEmpty() )
            return false;
        matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public boolean genderValidate(final String text){
        pattern = Pattern.compile(GenderValidator);
        if( text == null || text.isEmpty() )
            return false;
        matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public boolean phoneNumberValidate(final String text){
        pattern = Pattern.compile(PhoneNumberValidator);
        if( text == null || text.isEmpty() )
            return false;
        matcher = pattern.matcher(text);
        return matcher.matches();
    }

    public String validationMessage (boolean lastName, boolean firstName, boolean age, boolean gender, boolean phoneNumber){
        String LN ="";
        String FN ="";
        String AGE ="";
        String GEN ="";
        String PN ="";

        if (!lastName){ LN = " Last Name must contain more then five letters! (server) "; }
        if (!firstName){ FN = " First Name must contain more then two letters! (server) "; }
        if (!age){ AGE = " Age should not be greater than 200! (server) "; }
        if (!gender){ GEN = " Gender must be male or female! (server) "; }
        if (!phoneNumber){ PN = " Phone Number must contain +(xxx) xxx-xx-xx! (server) "; }

        return LN + FN + AGE + GEN + PN;
    }
}
