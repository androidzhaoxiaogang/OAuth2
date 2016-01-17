package it.seiton.oauth2.common.oauth2;

import android.content.SharedPreferences;

import it.seiton.oauth2.data.api.Oauth2Config;
import rx.Observable;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * Created by lukasw44 on 16.01.16.
 */
public class Oauth2Service {

    private static final String REFRESH_TOKEN = "oauth2_refresh";
    private static final String ACCESS_TOKEN = "oauth2_access";


    private final Oauth2Api oauth2Api;
    private Oauth2Config config;

    private SharedPreferences sharedPreferences;

    private String accessToken;
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Oauth2Service(Oauth2Api oauth2Api, Oauth2Config config, SharedPreferences sharedPreferences) {
        this.oauth2Api = oauth2Api;
        this.config = config;
        this.sharedPreferences = sharedPreferences;

        this.accessToken = sharedPreferences.getString(ACCESS_TOKEN, null);
        this.refreshToken = sharedPreferences.getString(REFRESH_TOKEN, null);
    }

    public Observable<AccessTokenResponse> login(final String username, final String password) {

        return oauth2Api.login(
                username,
                password,
                config.getClientId(),
                config.getClientSecret(),
                GrantType.PASSWORD.code,
                config.getScopes())
                .flatMap(new Func1<AccessTokenResponse, Observable<AccessTokenResponse>>() {
                    @Override
                    public Observable<AccessTokenResponse> call(AccessTokenResponse accessTokenResponse) {
                        saveTokens(accessTokenResponse);
                        return Observable.just(accessTokenResponse);
                    }
                });
    }

    public Observable<RefreshTokenResponse> refresh() {

        String refresh = sharedPreferences.getString(REFRESH_TOKEN, null);

        return oauth2Api.refresh(
                refresh,
                config.getClientId(),
                config.getClientSecret(),
                GrantType.REFRESH_TOKEN.code)
                .flatMap(new Func1<RefreshTokenResponse, Observable<RefreshTokenResponse>>() {
                    @Override
                    public Observable<RefreshTokenResponse> call(RefreshTokenResponse refreshTokenResponse) {
                        saveTokens(refreshTokenResponse);
                        return Observable.just(refreshTokenResponse);
                    }
                });
    }

    private void saveTokens(Oauth2TokenResponse response) {
        Oauth2Service.this.refreshToken = response.getRefreshToken();
        Oauth2Service.this.accessToken = response.getAccessToken();

        sharedPreferences.edit().putString(REFRESH_TOKEN, refreshToken).apply();
        sharedPreferences.edit().putString(ACCESS_TOKEN, accessToken).apply();
    }

}
