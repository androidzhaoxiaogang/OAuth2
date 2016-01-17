package it.seiton.oauth2.data.api;

import android.content.res.Resources;
import android.text.TextUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import it.seiton.oauth2.Oauth2App;
import it.seiton.oauth2.R;
import it.seiton.oauth2.common.oauth2.BearerAuthenticationInterceptor;
import it.seiton.oauth2.common.oauth2.BearerTokenAuthenticator;
import it.seiton.oauth2.common.oauth2.Oauth2Service;
import it.seiton.oauth2.di.ApplicationScope;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.MoshiConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import timber.log.Timber;

/**
 * Created by lukasw44 on 17.01.16.
 */
@Module(includes = Oauth2ApiModule.class)
public final class ApplicationApiModule {

    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    @Provides
    @ApplicationScope
    public UsersService provideUsersService(Retrofit retrofit) {
        return retrofit.create(UsersService.class);
    }

    @Provides
    @ApplicationScope
    Oauth2Config provideOauth2Config(Oauth2App app) {

        final Resources res = app.getResources();



        Oauth2Config oauth2Config = Oauth2Config.builder()
                .clientId(res.getString(R.string.oauth2_client_id))
                .clientSecret(res.getString(R.string.oauth2_client_secret))
                .endpoint(res.getString(R.string.oauth2_endpoints))
                .scopes(res.getStringArray(R.array.oauth2_scopes))
                .build();

        Timber.i("Oauth2Config: %s", oauth2Config);
        return oauth2Config;
    }

    @Provides
    @ApplicationScope
    Cache provideHttpCache(Oauth2App app) {
        final File cacheDir = new File(app.getCacheDir(), "http_cache");
        return new Cache(cacheDir, DISK_CACHE_SIZE);
    }

    @Provides
    @ApplicationScope
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, MoshiConverterFactory converterFactory, Oauth2Config config) {
        return new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(config.getEndpoint())
                .build();
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(Cache cache, final Oauth2Service oauth2Service) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(new BearerAuthenticationInterceptor(oauth2Service))
                .addInterceptor(loggingInterceptor)
                .authenticator(new BearerTokenAuthenticator(oauth2Service))
                .build();
    }
}
