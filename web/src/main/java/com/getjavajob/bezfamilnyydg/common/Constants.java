package com.getjavajob.bezfamilnyydg.common;

public class Constants {

    //NAME OF BEANS AND OTHER META INFORMATION
    public static final String DB_PROPERTIES = "PropertiesFileName";
    public static final String NAME_OF_POOL_CONN_IN_ATTRs_APP_SCOPE = "poolOfConnections";
    public static final String ACCOUNT_SERVICE = "AccountService";
    public static final String COOKIE_USER_ID = "UserId";
    public static final String COOKIE_JSESSIONID = "JSESSIONID";
    public static final String[] ALLOWED_ENDS_FOR_URLs = {"Search", "CreateAccount", "LogIn", "LogInFailed",
            "LogInLogic", "CreateAccountLogic", "css", "html", "js", "jpg", "css.map"};

    //NAMES OF PARAMS FROM REQUEST
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    public static final String IS_REMEMBER_ME = "RememberMe";
    public static final String ID = "id";
    public static final String PATTERN_FROM_SEARCH = "pattern";
    public static final String AVATAR_AS_PART_REQUEST = "avatar";

    //NAMES OF PARAMS FOR RESPONSE AND SESSION
    public static final String ACCOUNT_ID_FROM_REQUEST = "accountIdFromRequest";
    public static final String AVATAR = "Avatar";
    public static final String IS_FRIENDS = "isFriends";
    public static final String IS_SUBSCRIBED = "isSubscribed";
    public static final String IS_LOGGED = "isLogged";
    public static final String CURRENT_ACCOUNT = "Account";
    public static final String IS_LOGGED_FAILED = "IsLoggedFailed";
    public static final String DTO_ACCOUNT_AVATAR_MAP = "DTOAccountAvatarMap";
    public static final String COUNT_OF_RESULT_SEARCH_PAGES = "CountPagesWithResultSearch";
    public static final String COUNT_ACCOUNTS_PER_PAGE = "CountAccountsPerPage";
    public static final String CURRENT_PATTERN_FOR_SEARCH = "CurrentPatternForSearch";

    //NAMES OF JSP PAGES
    public static final String PERSONAL_PAGE = "PersonalPage";
    public static final String DISPLAY_AVATAR_AND_LINKS = "DisplayAvatarAndLinks";
    public static final String GAME = "Game";
    public static final String LOG_IN_PAGE = "LogIn";
    public static final String CREATE_PAGE = "CreateAccount";
    public static final String EDIT_ACCOUNT = "EditAccount";
    public static final String SEARCH_RESULT = "SearchAndResult";

}
