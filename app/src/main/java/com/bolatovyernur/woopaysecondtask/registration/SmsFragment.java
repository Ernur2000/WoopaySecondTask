package com.bolatovyernur.woopaysecondtask.registration;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentSmsBinding;

public class SmsFragment extends Fragment implements SmsView{
    FragmentSmsBinding fragmentSmsBinding;
    private SmsPresenter smsPresenter;
    private RegistrationPresenter registrationPresenter;
    private String login;
    private String email;
    private int textLength = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSmsBinding = FragmentSmsBinding.inflate(inflater,container,false);
        return fragmentSmsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getArgument();
        Timer();
        onBackPressed();
        onNextPressed();
        changedText();
        SendNewSms();
    }
    public void changedText(){
        fragmentSmsBinding.edSmsCode.addTextChangedListener(new WrapperTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = fragmentSmsBinding.edSmsCode.getText().toString();
                textLength = fragmentSmsBinding.edSmsCode.length();
                if (text.endsWith(" ")){
                    return;
                }
                if (textLength == 4) {
                    fragmentSmsBinding.edSmsCode.setText(new StringBuilder(text).insert(textLength - 1, " ").toString());
                    fragmentSmsBinding.edSmsCode.setSelection(textLength);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (fragmentSmsBinding.edSmsCode.getText().toString().trim().length() ==7){
                    fragmentSmsBinding.btnNext.setEnabled(true);
                }
                else fragmentSmsBinding.btnNext.setEnabled(false);
            }
        }); }
    public void getArgument(){
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            login = bundle.getString("Auth");
            email = bundle.getString("Email");
        }
    }
    public void onBackPressed(){
        fragmentSmsBinding.back.setOnClickListener(view1 -> {
            Navigation.findNavController(view1).
                    navigate(R.id.action_smsFragment_to_registrationFragment);
        });
    }
    public void onNextPressed(){
        smsPresenter = new SmsPresenter();
        fragmentSmsBinding.btnNext.setOnClickListener(view1 -> {
            smsPresenter.sendSms(login,fragmentSmsBinding.edSmsCode.getText().toString().replace(" ",""),this.getView());
        });
    }
    public void SendNewSms(){
        registrationPresenter = new RegistrationPresenter();
        fragmentSmsBinding.btnNewCode.setOnClickListener(view -> {
            registrationPresenter.register(login,email,view);
        });
    }
    public void Timer(){
        new CountDownTimer(60000,1000){
            @Override
            public void onTick(long l) {
                fragmentSmsBinding.btnNewCode.setText(new StringBuilder().append(getString(R.string.new_code)).append(l / 1000).toString());
            }
            @Override
            public void onFinish() {
                fragmentSmsBinding.btnNewCode.setText("Отправить код повторно");
                fragmentSmsBinding.btnNewCode.setEnabled(true);
            }
        }.start();
    }
    @Override
    public void onSmsSuccessResponse(View view) {
    }

}