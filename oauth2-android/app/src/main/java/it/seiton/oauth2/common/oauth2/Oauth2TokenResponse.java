package it.seiton.oauth2.common.oauth2;

import com.squareup.moshi.Json;

/**
 * Created by lukasw44 on 17.01.16.
 */
public abstract class Oauth2TokenResponse {

    @Json(name = "access_token")
    private String accessToken;
    @Json(name = "refresh_token")
    private String refreshToken;

    public Oauth2TokenResponse() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
