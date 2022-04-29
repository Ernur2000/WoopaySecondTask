package com.bolatovyernur.woopaysecondtask.mainPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentMainPageBinding;
import com.bolatovyernur.woopaysecondtask.pinCodePage.PinCodePageFragment;
import com.bolatovyernur.woopaysecondtask.util.Constants;
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;

public class MainPageFragment extends Fragment {
    FragmentMainPageBinding binding;
    Bundle bundle;
    PinCodePageFragment fragment;
    PreferenceUtils preferenceUtils;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceUtils = new PreferenceUtils(getContext());
        MainPagePresenter mainPagePresenter = new MainPagePresenter();
        mainPagePresenter.getCategories(view);
        fragment = new PinCodePageFragment();

        String login = preferenceUtils.getString(Constants.KEY_EMAIL);
        String password = preferenceUtils.getString(Constants.KEY_PASSWORD);
        String pin = preferenceUtils.getString("passCode");
        bundle = new Bundle();

        if (login != null && password != null) {
            if (pin != null) {
                bundle.putString("flag", "enter");
            } else {
                bundle.putString("flag", "create");
            }
            Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_pinCodePageFragment, bundle);
        } else {
            Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_loginFragment);
        }
    }
}