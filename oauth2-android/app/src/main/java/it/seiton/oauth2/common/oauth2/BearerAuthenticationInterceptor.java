package it.seiton.oauth2.common.oauth2;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by lukasw44 on 16.01.16.
 */
public class BearerAuthenticationInterceptor implements Interceptor {



    private final Oauth2Service oauth2Service;

    public BearerAuthenticationInterceptor(Oauth2Service service) {
        this.oauth2Service = service;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String accessToken = oauth2Service.getAccessToken();

        Timber.w("Befarrer Interceptor ----> ::: " + accessToken);


        if (accessToken != null) {
            final Request request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .build();
            return chain.proceed(request);

        }
        return chain.proceed(chain.request());

    }
}
