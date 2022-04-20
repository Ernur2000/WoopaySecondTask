package com.bolatovyernur.woopaysecondtask.mainPage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentMainPageBinding;
import com.bolatovyernur.woopaysecondtask.registration.Registration.LoginView;
import com.bolatovyernur.woopaysecondtask.util.Constants;
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class MainPageFragment extends Fragment implements LoginView {
    FragmentMainPageBinding binding;
    PreferenceUtils preferenceUtils;
    private MainPagePresenter mainPagePresenter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceUtils = new PreferenceUtils(getContext());
        mainPagePresenter = new MainPagePresenter();
        mainPagePresenter.getCategories(view, binding.progressBar);
        String login = PreferenceUtils.getString(Constants.KEY_EMAIL);
        String password = PreferenceUtils.getString(Constants.KEY_PASSWORD);
        if (login != null && password != null) {
            try {
                String messageAfterDecrypt = AESCrypt.decrypt(login, password);
                Log.d("DecryptedMSG", messageAfterDecrypt);
                binding.tvLogin.setText(login);
                binding.tvPassword.setText(messageAfterDecrypt);
                mainPagePresenter.login(login, messageAfterDecrypt, getView());
            } catch (GeneralSecurityException e) {
                Toast.makeText(getContext(), "Incorrect password", Toast.LENGTH_LONG).show();
            }
        } else {
            Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_registrationFragment);
        }
        binding.btnLogout.setOnClickListener(view1 -> {
            PreferenceUtils.deleteData();
            Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_registrationFragment);
        });
        binding.readDb.setOnClickListener(view1 -> {
            mainPagePresenter.readDB(view);
        });
    }

    @Override
    public void onSuccessResponse(String login, String password, View view) {
        Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_pinCodePageFragment);
    }
}