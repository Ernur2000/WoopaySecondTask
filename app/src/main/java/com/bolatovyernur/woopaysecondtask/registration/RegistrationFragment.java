package com.bolatovyernur.woopaysecondtask.registration;

import static android.graphics.Color.rgb;

import static java.util.Objects.requireNonNull;

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
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentRegistrationBinding;

public class RegistrationFragment extends Fragment implements RegistrationView{
    FragmentRegistrationBinding fragmentRegistrationBinding;
    private RegistrationPresenter registrationPresenter;
    private Bundle bundle;
    private String auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRegistrationBinding = FragmentRegistrationBinding
                .inflate(inflater,container,false);
        return fragmentRegistrationBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ErrorResponse errorResponse = new ErrorResponse();
        registrationPresenter = new RegistrationPresenter(errorResponse);
        fragmentRegistrationBinding.btnNext.setOnClickListener(view1 ->
                registrationPresenter.register(fragmentRegistrationBinding.edLogin.getText().toString(),fragmentRegistrationBinding.edEmail.getText().toString(),this.getView())
        );
        auth = fragmentRegistrationBinding.edLogin.getText().toString();

//        fragmentRegistrationBinding.checkbox.setOnClickListener(view1 -> {
//            if(fragmentRegistrationBinding.checkbox.isChecked()) {
//                fragmentRegistrationBinding.btnNext.setEnabled(true);
//
//            }
//            else {
//                fragmentRegistrationBinding.btnNext.setEnabled(false);
//            }
//        });
//        EditText[] edList = {fragmentRegistrationBinding.edEmail, fragmentRegistrationBinding.edLogin};
//        CustomTextWatcher2 textWatcher = new CustomTextWatcher2(edList, fragmentRegistrationBinding.btnNext,fragmentRegistrationBinding.checkbox);
//        for (EditText editText : edList) editText.addTextChangedListener(textWatcher);
        fragmentRegistrationBinding.edEmail.addTextChangedListener(new WrapperTextWatcher(){
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String email = fragmentRegistrationBinding.edEmail.getText().toString().trim();
                String login = fragmentRegistrationBinding.edLogin.getText().toString().trim();
                fragmentRegistrationBinding.btnNext.setEnabled(!login.isEmpty()&&!email.isEmpty());
            }

//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                super.onTextChanged(charSequence, i, i1, i2);
//                String email = fragmentRegistrationBinding.edEmail.getText().toString().trim();
//                String login = fragmentRegistrationBinding.edLogin.getText().toString().trim();
//                CheckBox checkBox = fragmentRegistrationBinding.checkbox;
//                if (checkBox.isChecked() && !email.isEmpty() && !login.isEmpty()){
//                    fragmentRegistrationBinding.btnNext.setEnabled(true);
//                }
//                else {
//                    fragmentRegistrationBinding.btnNext.setEnabled(false);
//                }
//            }
        });
        fragmentRegistrationBinding.edLogin.addTextChangedListener(new WrapperTextWatcher(){
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String email = fragmentRegistrationBinding.edEmail.getText().toString().trim();
                String login = fragmentRegistrationBinding.edLogin.getText().toString().trim();
                fragmentRegistrationBinding.btnNext.setEnabled(!login.isEmpty()&&!email.isEmpty());
            }

        });
        fragmentRegistrationBinding.checkbox.addTextChangedListener(new WrapperTextWatcher(){
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String email = fragmentRegistrationBinding.edEmail.getText().toString().trim();
                String login = fragmentRegistrationBinding.edLogin.getText().toString().trim();
                CheckBox checkBox = fragmentRegistrationBinding.checkbox;
                if (checkBox.isChecked() && !email.isEmpty() && !login.isEmpty()){
                    fragmentRegistrationBinding.btnNext.setEnabled(true);
                }
                else {
                    fragmentRegistrationBinding.btnNext.setEnabled(false);
                }
            }

//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                super.onTextChanged(charSequence, i, i1, i2);
//                String email = fragmentRegistrationBinding.edEmail.getText().toString().trim();
//                String login = fragmentRegistrationBinding.edLogin.getText().toString().trim();
//                CheckBox checkBox = fragmentRegistrationBinding.checkbox;
//                if (checkBox.isChecked() && !email.isEmpty() && !login.isEmpty()){
//                    fragmentRegistrationBinding.btnNext.setEnabled(true);
//                }
//                else {
//                    fragmentRegistrationBinding.btnNext.setEnabled(false);
//                }
//
//            }
        });
        makeText();
    }
    private void makeText(){
        SpannableString ss = new SpannableString("Принимаю пользовательское \nсоглашение Wooppay");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                // TO DO
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.rgb(28, 131, 255));
            }
        };
        ss.setSpan(clickableSpan, 9, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView textView = fragmentRegistrationBinding.tvTermOfUse;
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onRegistrationSuccessResponse(View view) {
        bundle = new Bundle();
        bundle.putString("Auth",auth);
        Toast.makeText(view.getContext(), auth, Toast.LENGTH_SHORT).show();
//        Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_smsFragment,bundle);
    }
}