package com.prishan.apnabusiness.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constant {

    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    public static SimpleDateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);

    public static final String URL = "";

    public static final String DEVICEID = "deviceID";
    public static final String MESSAGE = "message";
    public static final String INVALID_SESSION_MESSAGE = "invalid session";
    public static final String PATH = "app_path";

    public static class Header {
        public static final String AUTH_ID = "X-ADN-AuthID";
        public static final String SESSION_ID = "X-ADN-SessionID";
    }

    public static class SharedPrefrence {

        public static class Session {
            public static final String EMAIL = "email";
            public static final String DESCRIPTION = "description";
            public static final String SESSION_REQUEST = "SessionRequest";
            public static final String SESSION_VALIDATE = "SessionValidate";
            public static final String SESSION_ID = "SessionID";
        }

        public static class User {
            public static final String USER = "user";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
        }

        public static class RO {
            public static final String DATA = "ro_data";
            public static final String ALL_MOVIES = "ro_all_movies";
            public static final String DISTRIBUTORS = "ro_distributors";
            public static final String TERRITORIES = "ro_territories";
            public static final String RATE_CARD = "ro_ratecard";
            public static final String START_DATE = "ro_start_date";
            public static final String END_DATE = "ro_end_date";
            public static final String ALL_USERS = "ro_all_user";
        }

        public static class RO_NEW {
            public static final String MOVIE = "ro_new_movie";
            public static final String DISTRIBUTOR = "ro_new_distributor";
            public static final String TERRITORY = "ro_new_territory";
            public static final String RATE_CARD = "ro_new_ratecard";
            public static final String START_DATE = "ro_new_start_date";
            public static final String END_DATE = "ro_new_end_date";
            public static final String IS_APPROVAL_NEEDED = "ro_new_is_approval_needed";
            public static final String NO_OF_SHOWS = "ro_new_no_of_show";
            public static final String USER = "ro_new_user";
            public static final String SCREENS = "ro_screens";
        }
    }

    public static class RO {

        public static class PERMISSION {
            public static final String CAN_ADD = "CAN_ADD";
        }

    }

    public static class RequestCode {

        public static int RO_DETAIL = 0001;
    }
}
