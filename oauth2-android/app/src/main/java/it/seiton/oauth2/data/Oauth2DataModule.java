package it.seiton.oauth2.data;

import android.app.Application;
import android.content.SharedPreferences;

import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import it.seiton.oauth2.Oauth2App;
import it.seiton.oauth2.data.api.ApplicationApiModule;
import it.seiton.oauth2.data.api.Oauth2ApiModule;
import it.seiton.oauth2.di.ApplicationScope;
import retrofit2.MoshiConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lukasw44 on 15.01.16.
 */
@Module(includes = {ApplicationApiModule.class})
public class Oauth2DataModule {

    @Provides
    @ApplicationScope
    SharedPreferences provideSharedPreferences(Oauth2App app) {
        return app.getSharedPreferences("oauth2", MODE_PRIVATE);
    }

    @Provides
    public Moshi provideMoshi() {
        return new Moshi.Builder().build();
    }

    @Provides
    public MoshiConverterFactory provideMoshiConverterFactory(Moshi moshi) {
        return MoshiConverterFactory.create(moshi);
    }
}
