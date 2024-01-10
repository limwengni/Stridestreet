/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jmok4
 */
public class validateInput {
    
    public static List<String> isPasswordValid(String password) {
        // add validation logic here
        List<String> errorMessages = new ArrayList<String>();
    
        if (password.length() > 15 || password.length() < 8){
            errorMessages.add("Password length should be between 8 and 15 characters.");
        }
    
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars )) {
            errorMessages.add("Password should contain at least one uppercase letter.");
        }
    
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars )) {
            errorMessages.add("Password should contain at least one lowercase letter.");
        }
    
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers )) {
            errorMessages.add("Password should contain at least one digit.");
        }
    
        String specialChars = "(.*[@,#,$,_,-,%,!].*$)";
        if (!password.matches(specialChars )) {
            errorMessages.add("Password should contain at least one special character (@, #, $, _,- or %).");
        }
    
        return errorMessages;
    }
    
    public static List<String> isPhoneNumberValid(String phone) {
        // add validation logic here
        List<String> errorMessages = new ArrayList<String>();
    
        String phoneValidate = "^(\\+?6?01)[02-46-9][-][0-9]{7}$|^(\\+?6?01)[1][-][0-9]{8}$";
        if (!phone.matches(phoneValidate )) {
            errorMessages.add("Contact number shoud be a format of 01x-xxxxxxxxx");
        }
    
        return errorMessages;
    }
    
    public static List<String> isEmailAddressValid(String email) {
        // add validation logic here
        List<String> errorMessages = new ArrayList<String>();
    
        String emailValidate = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailValidate )) {
            errorMessages.add("Email Address format should be xxxxxxxx@xxxxxx.xxx");
        }
    
        return errorMessages;
    }
    
    public static List<String> isUserNameValid(String username) {
        // add validation logic here
        
        List<String> errorMessages = new ArrayList<String>();
        
        String upperCaseChars = "(.*[A-Z].*)";
        if (!username.matches(upperCaseChars )) {
            errorMessages.add("Username should contain at least one uppercase letter.");
        }
    
        String lowerCaseChars = "(.*[a-z].*)";
        if (!username.matches(lowerCaseChars )) {
            errorMessages.add("Username should contain at least one lowercase letter.");
        }
    
        return errorMessages;
    }
    
    public static List<String> isNameValid(String name) {
        // add validation logic here
        List<String> errorMessages = new ArrayList<String>();
        
        String upperCaseChars = "(.*[A-Z].*)";
        if (!name.matches(upperCaseChars )) {
            errorMessages.add("name should contain at least one uppercase letter.");
        }
    
        String lowerCaseChars = "(.*[a-z].*)";
        if (!name.matches(lowerCaseChars )) {
            errorMessages.add("name should contain at least one lowercase letter.");
        }
    
        return errorMessages;
    }
    
}
