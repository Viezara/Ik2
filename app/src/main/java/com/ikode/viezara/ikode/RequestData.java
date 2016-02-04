package com.ikode.viezara.ikode;


import android.content.SharedPreferences;

public class RequestData {

    //Address of our scripts of the CRUD
    /*
    public static final String URL_GET_DATA = "http://www.darknz.5gbfree.com/android/getData.php?id=";
    public static final String URL_GET_DATA_IMAGE = "http://www.darknz.5gbfree.com/android/getDataImage.php?id=";
    //public static final String URL_ADD="http://www.darkuz.5gbfree.com/android/VerifyCode.php";
    public static final String URL_GET_SECURITY_NUMBER="http://www.darknz.5gbfree.com/android/getSecurityNumber.php?securityNumber=";
    public static final String URL_ADD_USER="http://www.darknz.5gbfree.com/android/addUser.php";
    public static final String URL_GET_CONNECT_USER = "http://www.darknz.5gbfree.com/android/getUser.php";
    */
    //local server
    public static final String URL_GET_DATA = "http://219.89.205.123/android/getData.php?id=";
    public static final String URL_GET_DATA_IMAGE = "http://219.89.205.123/android/getDataImage.php?id=";
    //public static final String URL_ADD="http://www.darkuz.5gbfree.com/android/VerifyCode.php";
    public static final String URL_GET_SECURITY_NUMBER="http://219.89.205.123/android/getSecurityNumber.php?securityNumber=";
    public static final String URL_ADD_USER="http://219.89.205.123/android/addUser.php";
    public static final String URL_GET_CONNECT_USER = "http://219.89.205.123/android/getUser.php";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_DISPLAY_SECURITY_CODE = "securityCode";
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_MSG = "message";

    public static final String TAG_TYPE = "type";
    public static final String TAG_DESC = "description";
    public static final String TAG_VER = "Version";
    public static final String TAG_SECURITY_NUMBER = "securityNumber";
    public static final String TAG_SERIAL_NUMBER = "phoneSerial";

    public static final String KEY_Security_Number = "Security_Number";
    public static final String KEY_User_Email = "user_Email";
    public static final String KEY_User_Name = "user_Name";
    public static final String Key_User_Serial_Code="serial_code";

    //employee id to pass with intent
    public static final String barcode_ID = "barcode_ID";
    //public static final String phone_code = "phone_code";
    public static final String display_code = "code";

    //session common variable
    public static final String SESSION = "Session" ;
    public static final String DEFAULT_SESSION_VALUE = "NULL";
    public static final String SESSION_CODE = "User";

    public static boolean getAccountsPhoneNumber(String phoneNumber) {
        String[][] AREA_CODES = {
                {"PHILIPPINES", "+63"},
                {"NEW ZEALAND", "+64"},
                {"DEFAULT", "+"}
        };

        boolean counter = false;

        for(int x = 0; x < AREA_CODES.length; x++){

            if(phoneNumber.startsWith(AREA_CODES[x][1])){
                counter = true;
            }
        }
        return counter;
    }

    public static boolean checkSession(SharedPreferences sp) {
        String SHARED_SESSION_VALUE = sp.getString(SESSION_CODE, DEFAULT_SESSION_VALUE);
        boolean SESSION_CHECKER = false;

        if(!SHARED_SESSION_VALUE.equals(DEFAULT_SESSION_VALUE)) {
            SESSION_CHECKER = true;
        }

        return SESSION_CHECKER;
    }
}
