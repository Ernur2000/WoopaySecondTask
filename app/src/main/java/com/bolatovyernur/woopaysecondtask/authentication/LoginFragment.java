package com.bolatovyernur.woopaysecondtask.authentication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentLoginBinding;
import com.bolatovyernur.woopaysecondtask.util.WrapperTextWatcher;

public class LoginFragment extends Fragment implements AuthView{
    FragmentLoginBinding fragmentLoginBinding;
    private AuthPresenter authPresenter;
    private int textLength = 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater,container,false);
        return  fragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onNextPressed();
        makeText();
        changeText();
    }
    public void onNextPressed(){
        authPresenter = new AuthPresenter();
        fragmentLoginBinding.btnNext.setOnClickListener(view1 -> authPresenter.login(fragmentLoginBinding.edLogin.getText().toString(),fragmentLoginBinding.edPassword.getText().toString(),getView()));
    }
    public void makeText(){
        SpannableString ss = new SpannableString("У меня еще нет аккаунта");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.rgb(28, 131, 255));
            }
        };
        ss.setSpan(clickableSpan, 7, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView textView = fragmentLoginBinding.tvNoAcc;
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    public void changeText(){
        fragmentLoginBinding.edPassword.addTextChangedListener(new WrapperTextWatcher(){
            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String login = fragmentLoginBinding.edLogin.getText().toString().trim();
                String password = fragmentLoginBinding.edPassword.getText().toString().trim();
                fragmentLoginBinding.btnNext.setEnabled(login.length()==15 && password.length()>=8);
            }
        });
        fragmentLoginBinding.edLogin.addTextChangedListener(new WrapperTextWatcher(){
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String login = fragmentLoginBinding.edLogin.getText().toString().trim();
                String password = fragmentLoginBinding.edPassword.getText().toString().trim();
                fragmentLoginBinding.btnNext.setEnabled(login.length()==15 && password.length()>=8);
                String text = fragmentLoginBinding.edLogin.getText().toString();
                textLength = fragmentLoginBinding.edLogin.getText().length();
                if (text.endsWith(" ") || (text.endsWith("+"))){
                    return;
                }
                switch (textLength){
                    case 1:
                        fragmentLoginBinding.edLogin.setText(new StringBuilder(text).insert(textLength-1, "+").toString());
                        fragmentLoginBinding.edLogin.setSelection(fragmentLoginBinding.edLogin.getText().length());
                        break;
                    case 6:
                    case 10:
                    case 13:
                        fragmentLoginBinding.edLogin.setText(new StringBuilder(text).insert(textLength-1, " ").toString());
                        fragmentLoginBinding.edLogin.setSelection(fragmentLoginBinding.edLogin.getText().length());
                        break;
                }

            }
        });
    }

    @Override
    public void onSuccessResponse(View view) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainPageFragment);
    }
}