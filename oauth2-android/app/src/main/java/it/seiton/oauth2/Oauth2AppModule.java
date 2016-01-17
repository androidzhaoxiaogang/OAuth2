package it.seiton.oauth2;

import dagger.Module;
import dagger.Provides;
import it.seiton.oauth2.di.ApplicationScope;

/**
 * Created by lukasw44 on 15.01.16.
 */
@Module
public final class Oauth2AppModule {

    private final Oauth2App app;

    public Oauth2AppModule(Oauth2App app) {
        this.app = app;
    }

    @Provides
    @ApplicationScope
    Oauth2App provideApplication() {
        return app;
    }

}
