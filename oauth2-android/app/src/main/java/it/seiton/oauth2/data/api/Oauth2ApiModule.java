package it.seiton.oauth2.data.api;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;

import com.squareup.moshi.Moshi;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import it.seiton.oauth2.Oauth2App;
import it.seiton.oauth2.R;
import it.seiton.oauth2.common.oauth2.BasicAuthenticationInterceptor;
import it.seiton.oauth2.common.oauth2.BearerAuthenticationInterceptor;
import it.seiton.oauth2.common.oauth2.BearerTokenAuthenticator;
import it.seiton.oauth2.common.oauth2.Oauth2;
import it.seiton.oauth2.common.oauth2.Oauth2Api;
import it.seiton.oauth2.common.oauth2.Oauth2Service;
import it.seiton.oauth2.di.ApplicationScope;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.MoshiConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by lukasw44 on 15.01.16.
 */
@Module
public final class Oauth2ApiModule {

    @Provides
    @ApplicationScope
    @Oauth2
    OkHttpClient provideOauth2OkHttpClient(Oauth2Config oauth2Config) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new BasicAuthenticationInterceptor(oauth2Config.getClientId(), oauth2Config.getClientSecret()))
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @ApplicationScope
    @Oauth2
    Retrofit provideOauth2Retrofit(@Oauth2 OkHttpClient okHttpClient, Oauth2Config config, MoshiConverterFactory converterFactory) {

        return new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(config.getEndpoint())
                .build();

    }

    @Provides
    @ApplicationScope
    @Oauth2
    Oauth2Api provideOauth2Api(@Oauth2 Retrofit retrofit) {
        return retrofit.create(Oauth2Api.class);
    }

    @Provides
    @ApplicationScope
    Oauth2Service provideOauth2Service(@Oauth2 Oauth2Api api, Oauth2Config config, SharedPreferences sharedPreferences) {
        return new Oauth2Service(api, config, sharedPreferences);
    }

}
