package it.seiton.oauth2.data.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lukasw44 on 17.01.16.
 */
public interface UsersService {

    @GET("/api/users")
    @Headers({

            "Accept: application/json"

    })
    Observable<UsersResponse> getUsers(@Query("page") int page, @Query("size") int size);
}
