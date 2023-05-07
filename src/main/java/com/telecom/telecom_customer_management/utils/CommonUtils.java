package com.telecom.telecom_customer_management.utils;

import com.telecom.telecom_customer_management.constants.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    public static boolean validateAadhar(String aadhar){
        if(aadhar == null || aadhar.isEmpty()) return false;
        Pattern p = Pattern.compile(Constants.MOBILE_REGEX);
        Matcher m = p.matcher(aadhar);
        return (m.matches());
    }

    public static boolean validateMobile(String mobile){
        if(mobile == null || mobile.isEmpty()) return false;
        Pattern p = Pattern.compile(Constants.MOBILE_REGEX);
        Matcher m = p.matcher(mobile);
        return (m.matches());
    }
}
