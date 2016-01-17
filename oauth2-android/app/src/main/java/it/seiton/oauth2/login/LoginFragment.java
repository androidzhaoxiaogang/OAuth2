package it.seiton.oauth2.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import it.seiton.oauth2.MainActivity;
import it.seiton.oauth2.R;
import it.seiton.oauth2.common.BaseFragment;
import it.seiton.oauth2.common.oauth2.AccessTokenResponse;
import it.seiton.oauth2.common.oauth2.Oauth2Service;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by lukasw44 on 15.01.16.
 */
public class LoginFragment extends BaseFragment {

    private static final String TAG = "LoginFragment";

    @Bind(R.id.username)
    TextView username;

    @Bind(R.id.password)
    TextView password;


    @Inject
    Oauth2Service oauth2Service;

    public static LoginFragment newInstance() {
        final LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    protected int fragmentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void onFragmentCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "-->on create fragment Oauth2Service is nul : " + (oauth2Service == null));
        //X((MainActivity) getActivity()).component().inject(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).component().inject(this);


    }

    @OnClick(R.id.login_button)
    public void onLoginClick(View view) {

        final String username = this.username.getText().toString();
        final String password = this.password.getText().toString();

        oauth2Service.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AccessTokenResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AccessTokenResponse accessTokenResponse) {

                        Timber.i("Login success %s", accessTokenResponse);
                        ((MainActivity) getActivity()).goToUsers();
                    }
                });
    }
}
