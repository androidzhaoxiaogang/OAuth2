package it.seiton.oauth2.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by lukasw44 on 15.01.16.
 */
public abstract class BaseFragment extends Fragment {

    @LayoutRes
    protected abstract int fragmentLayout();

    protected abstract void onFragmentCreate(@Nullable Bundle savedInstanceState);

    public String fragmentTag() {
        return "FRAGMENT_TAG_" + this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        onFragmentCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(fragmentLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
