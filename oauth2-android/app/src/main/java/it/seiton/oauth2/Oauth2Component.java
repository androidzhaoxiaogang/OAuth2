package it.seiton.oauth2;

import dagger.Component;
import it.seiton.oauth2.data.Oauth2DataModule;
import it.seiton.oauth2.di.ApplicationScope;
import it.seiton.oauth2.di.Oauth2Graph;

/**
 * Created by lukasw44 on 15.01.16.
 */
@ApplicationScope
@Component(modules = {Oauth2AppModule.class, Oauth2DataModule.class})
public interface Oauth2Component extends Oauth2Graph {

    final class Initializer {

        static Oauth2Component init(Oauth2App app) {
            return DaggerOauth2Component.builder()
                    .oauth2AppModule(new Oauth2AppModule(app))
                    .build();
        }

        private Initializer() {
        }
    }
}
