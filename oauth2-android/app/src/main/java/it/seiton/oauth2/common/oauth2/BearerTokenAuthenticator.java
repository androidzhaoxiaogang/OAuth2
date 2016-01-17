package it.seiton.oauth2.common.oauth2;


import java.io.IOException;
import java.net.Proxy;
import java.util.Timer;


import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import timber.log.Timber;

/**
 * Created by lukasw44 on 16.01.16.
 */
public class BearerTokenAuthenticator implements Authenticator {

    private Oauth2Service oauth2Service;

    public BearerTokenAuthenticator(Oauth2Service oauth2Service) {
        this.oauth2Service = oauth2Service;
    }


    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }

        return result;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        Timber.w("Authenticate ---->");

        if (responseCount(response) >= 3) {
            return null; // If we've failed 3 times, give up.
        }

        final String credential = "Bearer " + oauth2Service.refresh().toBlocking().first().getAccessToken();
        if (credential.equals(response.request().header("Authorization"))) {
            return null; // If we already failed with these credentials, don't retry.
        }

        return response.request().newBuilder()
                .header("Authorization", credential)
                .build();
    }
}
