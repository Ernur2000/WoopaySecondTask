package com.bolatovyernur.woopaysecondtask.registration.Password;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentPasswordBinding;
import com.bolatovyernur.woopaysecondtask.registration.Registration.LogAfterRegView;
import com.bolatovyernur.woopaysecondtask.util.WrapperTextWatcher;

import java.util.regex.Pattern;

public class PasswordFragment extends Fragment implements PasswordView, LogAfterRegView {
    private PasswordPresenter passwordPresenter;
    FragmentPasswordBinding fragmentPasswordBinding;
    private String activationCode;
    private String login;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    ".{8,}" +               //at least 4 characters
                    "$");

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPasswordBinding = FragmentPasswordBinding.inflate(inflater, container, false);
        return fragmentPasswordBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        passwordPresenter = new PasswordPresenter();
        super.onViewCreated(view, savedInstanceState);
        onBackPressed();
        createWallet();
        getArgument();
        changeText();
    }

    public void onBackPressed() {
        fragmentPasswordBinding.back.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_passwordFragment_to_smsFragment));
    }

    public void createWallet() {
        fragmentPasswordBinding.btnCreateWallet.setOnClickListener(view1 -> {
            passwordPresenter.createPassword(login, activationCode, fragmentPasswordBinding.edPickPassword.getText().toString(), this.getView());
            //passwordPresenter.loginAfterRegister(login,fragmentPasswordBinding.edPickPassword.getText().toString(),getView());
        });
    }

    public void getArgument() {
        if (getArguments() != null) {
            activationCode = getArguments().getString("activationCode");
            login = getArguments().getString("Auth");
        }
    }

    public void changeText() {
        fragmentPasswordBinding.edPickPassword.addTextChangedListener(new WrapperTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String password1 = fragmentPasswordBinding.edPickPassword.getText().toString().trim();
                String password2 = fragmentPasswordBinding.edRepeatPassword.getText().toString().trim();
                fragmentPasswordBinding.btnCreateWallet.setEnabled(password1.equals(password2) && PASSWORD_PATTERN.matcher(password1).matches() && PASSWORD_PATTERN.matcher(password2).matches());
            }
        });
        fragmentPasswordBinding.edRepeatPassword.addTextChangedListener(new WrapperTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String password1 = fragmentPasswordBinding.edPickPassword.getText().toString().trim();
                String password2 = fragmentPasswordBinding.edRepeatPassword.getText().toString().trim();
                fragmentPasswordBinding.btnCreateWallet.setEnabled(password1.equals(password2) && PASSWORD_PATTERN.matcher(password1).matches() && PASSWORD_PATTERN.matcher(password2).matches());
            }
        });
    }

    @Override
    public void onSuccessResponse(View view) {
        Toast.makeText(view.getContext(), "You successfully authorized", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessLogin(View view) {
        Navigation.findNavController(view).navigate(R.id.action_passwordFragment_to_mainPageFragment);
    }
}