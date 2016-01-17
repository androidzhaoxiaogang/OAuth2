package it.seiton.oauth2;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

/**
 * Created by lukasw44 on 15.01.16.
 */

public class Oauth2App extends Application {

    private Oauth2Component component;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO Crashlytics.start(this);
            // TODO Timber.plant(new CrashlyticsTree());
        }

        buildComponentAndInject();
    }

    public static Oauth2App get(Context context) {
        return (Oauth2App) context.getApplicationContext();
    }

    public Oauth2Component component() {
        return component;
    }

    private void buildComponentAndInject() {
        component = Oauth2Component.Initializer.init(this);
        component.inject(this);
    }

}


