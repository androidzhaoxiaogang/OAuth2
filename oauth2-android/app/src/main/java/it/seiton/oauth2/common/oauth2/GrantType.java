package it.seiton.oauth2.common.oauth2;

/**
 * Created by lukasw44 on 16.01.16.
 */
public enum GrantType {

    PASSWORD("password"),
    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION("authorization_code"),
    IMPLICIT("implicit");

    public final String code;

    GrantType(String code) {
        this.code = code;
    }
}
