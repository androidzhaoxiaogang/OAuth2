package it.seiton.oauth2.common.oauth2;



import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by lukasw44 on 16.01.16.
 */
public class BasicAuthenticationInterceptor implements Interceptor {

    private final String userName;
    private final String password;

    public BasicAuthenticationInterceptor(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request()
                .newBuilder()
                .addHeader("Authorization", Credentials.basic(userName, password))
                .build();

        return chain.proceed(request);
    }

}
