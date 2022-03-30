package com.bolatovyernur.woopaysecondtask.registration;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bolatovyernur.woopaysecondtask.databinding.FragmentRegistrationBinding;

public class RegistrationFragment extends Fragment implements RegistrationView{
    FragmentRegistrationBinding fragmentRegistrationBinding;
    private RegistrationPresenter registrationPresenter;
    private int textLength = 0;

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
        onNextPressed();
        changeText();
        makeText();
    }
    public void onNextPressed(){
        registrationPresenter = new RegistrationPresenter();
        fragmentRegistrationBinding.btnNext.setOnClickListener(view1 ->
                registrationPresenter.register(fragmentRegistrationBinding.edLogin.getText().toString().replace(" ",""),fragmentRegistrationBinding.edEmail.getText().toString(),this.getView())
        );
    }
    public void changeText(){
        fragmentRegistrationBinding.checkbox.setOnClickListener(view1 -> {
            String email = fragmentRegistrationBinding.edEmail.getText().toString().trim();
            String login = fragmentRegistrationBinding.edLogin.getText().toString().trim();
            if(fragmentRegistrationBinding.checkbox.isChecked()) {
                if(login.length()==15&&email.length()>=8){
                    fragmentRegistrationBinding.btnNext.setEnabled(true);
                }
            }
            else {
                fragmentRegistrationBinding.btnNext.setEnabled(false);
            }
        });
        fragmentRegistrationBinding.edEmail.addTextChangedListener(new WrapperTextWatcher(){
            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String email = fragmentRegistrationBinding.edEmail.getText().toString().trim();
                String login = fragmentRegistrationBinding.edLogin.getText().toString().trim();
                if(fragmentRegistrationBinding.checkbox.isChecked()) {
                    if(login.length()==15&&email.length()>=8){
                        fragmentRegistrationBinding.btnNext.setEnabled(true);
                    }
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
//            }
        });
        fragmentRegistrationBinding.edLogin.addTextChangedListener(new WrapperTextWatcher(){
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String email = fragmentRegistrationBinding.edEmail.getText().toString().trim();
                String login = fragmentRegistrationBinding.edLogin.getText().toString().trim();
                if(fragmentRegistrationBinding.checkbox.isChecked()) {
                    if(login.length()==15&&email.length()>=8){
                        fragmentRegistrationBinding.btnNext.setEnabled(true);
                    }
                }
                else {
                    fragmentRegistrationBinding.btnNext.setEnabled(false);
                }
                String text = fragmentRegistrationBinding.edLogin.getText().toString();
                textLength = fragmentRegistrationBinding.edLogin.getText().length();
                if (text.endsWith(" ") || (text.endsWith("+"))){
                    return;
                }
                switch (textLength){
                    case 1:
                        fragmentRegistrationBinding.edLogin.setText(new StringBuilder(text).insert(textLength-1, "+").toString());
                        fragmentRegistrationBinding.edLogin.setSelection(fragmentRegistrationBinding.edLogin.getText().length());
                        break;
                    case 6:
                    case 10:
                    case 13:
                        fragmentRegistrationBinding.edLogin.setText(new StringBuilder(text).insert(textLength-1, " ").toString());
                        fragmentRegistrationBinding.edLogin.setSelection(fragmentRegistrationBinding.edLogin.getText().length());
                        break;
                }

            }
        });
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

    }
}