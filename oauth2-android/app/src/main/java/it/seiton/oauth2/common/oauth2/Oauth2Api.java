package it.seiton.oauth2.common.oauth2;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lukasw44 on 17.01.16.
 */
public interface Oauth2Api {

    @POST("/oauth/token")
    @FormUrlEncoded
    Observable<AccessTokenResponse> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grantType,
            @Field("scope") String scope
    );

    @POST("/oauth/token")
    @FormUrlEncoded
    Observable<RefreshTokenResponse> refresh(
            @Field("refresh_token") String refreshToken,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grantType
    );

    @POST("/logout")
    Observable<LogoutResponse> logout();

}
