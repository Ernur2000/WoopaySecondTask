package com.bolatovyernur.woopaysecondtask.registration;

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

public class PasswordFragment extends Fragment implements PasswordView {
    private PasswordPresenter passwordPresenter;
    FragmentPasswordBinding fragmentPasswordBinding;
    private String activationCode;
    private String login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPasswordBinding = FragmentPasswordBinding.inflate(inflater, container, false);
        return fragmentPasswordBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ErrorResponse errorResponse = new ErrorResponse();
        passwordPresenter = new PasswordPresenter(errorResponse);
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            activationCode = getArguments().getString("activationCode");
            login = getArguments().getString("Auth");
        }
        fragmentPasswordBinding.edPickPassword.addTextChangedListener(new WrapperTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                //заглваные буквы, латиница
                //text > 8
            }
        });
        fragmentPasswordBinding.edRepeatPassword.addTextChangedListener(new WrapperTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                //совпадает с первым
            }
        });
        fragmentPasswordBinding.back.setOnClickListener(view1 -> {
            Navigation.findNavController(view1).navigate(R.id.action_passwordFragment_to_smsFragment);
        });

        fragmentPasswordBinding.btnCreateWallet.setOnClickListener(view1 -> {
            passwordPresenter.createPassword(login, activationCode, fragmentPasswordBinding.edPickPassword.getText().toString(), this.getView());
        });

    }

    @Override
    public void onSuccessResponse(View view) {
        Toast.makeText(view.getContext(), "You successfully authorized", Toast.LENGTH_SHORT).show();
    }
}