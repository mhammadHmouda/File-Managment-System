package app.model;

public class ConstantModel {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 12*60*60;//12 hours
    public static final String SIGNING_KEY = "36019123screen";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAIL = "fail";

    public static final String ENTRY_STATUS_ACCEPTED = "Accepted";
    public static final String ENTRY_STATUS_REJECTED = "Rejected";
    public static final String ENTRY_STATUS_POSITIVE = "Positive";
    public static final String ENTRY_STATUS_NEGATIVE = "Negative";
}
