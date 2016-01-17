package it.seiton.oauth2.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import it.seiton.oauth2.MainActivity;
import it.seiton.oauth2.R;
import it.seiton.oauth2.common.BaseFragment;
import it.seiton.oauth2.data.api.UsersResponse;
import it.seiton.oauth2.data.api.UsersService;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by lukasw44 on 17.01.16.
 */
public class UsersFragment extends BaseFragment {

    @Inject
    UsersService usersService;

    public static UsersFragment newInstance() {
        UsersFragment fragment = new UsersFragment();
        return fragment;
    }

    @Override
    protected int fragmentLayout() {
        return R.layout.fragment_users;
    }

    @Override
    protected void onFragmentCreate(@Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).component().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        usersService.getUsers(0, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UsersResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UsersResponse usersResponse) {

                    }
                });
    }
}
