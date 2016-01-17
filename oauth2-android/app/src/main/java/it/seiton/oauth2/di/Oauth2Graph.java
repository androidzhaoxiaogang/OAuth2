package it.seiton.oauth2.di;

import it.seiton.oauth2.Oauth2App;
import it.seiton.oauth2.common.oauth2.Oauth2Service;
import it.seiton.oauth2.data.api.UsersService;

/**
 * Created by lukasw44 on 15.01.16.
 */
public interface Oauth2Graph {

    void inject(Oauth2App app);

    Oauth2Service getOauth2Service();

    UsersService provideUsersService();

}
