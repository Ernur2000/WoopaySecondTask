package com.bolatovyernur.woopaysecondtask.authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentAuthBinding;

public class AuthFragment extends Fragment {
    FragmentAuthBinding authBinding;
    private authPresenter authPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        authBinding = FragmentAuthBinding.inflate(inflater,container,false);
        return authBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authPresenter = new authPresenter();
        authBinding.btnLogin.setOnClickListener(view1 -> {
            authPresenter.login();
        });
    }
}