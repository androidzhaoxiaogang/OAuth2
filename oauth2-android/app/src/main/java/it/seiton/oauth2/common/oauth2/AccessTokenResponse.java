package it.seiton.oauth2.common.oauth2;

import com.squareup.moshi.Json;

/**
 * Created by lukasw44 on 16.01.16.
 */
public class AccessTokenResponse extends Oauth2TokenResponse {

    @Json(name = "expires_in")
    private long expiresIn;

    public AccessTokenResponse() {
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "AccessTokenResponse{" +
                "accessToken='" + getAccessToken() + '\'' +
                ", refreshToken='" + getRefreshToken() + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
