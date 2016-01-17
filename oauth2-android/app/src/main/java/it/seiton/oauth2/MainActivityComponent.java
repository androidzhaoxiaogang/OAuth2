package it.seiton.oauth2;

import dagger.Component;
import it.seiton.oauth2.di.MainActivityScope;
import it.seiton.oauth2.login.LoginFragment;
import it.seiton.oauth2.users.UsersFragment;

/**
 * Created by lukasw44 on 16.01.16.
 */
@MainActivityScope
@Component(
        dependencies = Oauth2Component.class
)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginFragment baseFragment);

    void inject(UsersFragment usersFragment);
}
