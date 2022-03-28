package com.bolatovyernur.woopaysecondtask.registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentSmsBinding;

import java.util.Collections;
import java.util.List;

public class SmsFragment extends Fragment implements SmsView{
    FragmentSmsBinding fragmentSmsBinding;
    private SmsPresenter smsPresenter;
    private String login;
    private ErrorResponse errorResponse;
    private String activationCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSmsBinding = FragmentSmsBinding.inflate(inflater,container,false);
        //getArgument();
        return fragmentSmsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        errorResponse = new ErrorResponse();
        smsPresenter = new SmsPresenter(errorResponse);
        activationCode = fragmentSmsBinding.edSmsCode.getText().toString();
        getArgument();
        Timer();
        onBackPressed();
        onNextPressed();
        changedText();
    }
    public void changedText(){
        fragmentSmsBinding.edSmsCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (fragmentSmsBinding.edSmsCode.getText().toString().trim().length() >=6){
                    fragmentSmsBinding.btnNext.setEnabled(true);
                }
                else fragmentSmsBinding.btnNext.setEnabled(false);
            }
        });
    }
    public void getArgument(){

            //login = getArguments().getString("Login");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            login = bundle.getString("Auth");
        }

    }


    public void onBackPressed(){
        fragmentSmsBinding.back.setOnClickListener(view1 -> {
            Navigation.findNavController(view1).
                    navigate(R.id.action_smsFragment_to_registrationFragment);
        });
    }

    public void onNextPressed(){
        fragmentSmsBinding.btnNext.setOnClickListener(view1 -> {
            Log.d("Error", "asdas");
            smsPresenter.sendSms(login,fragmentSmsBinding.edSmsCode.getText().toString(),this.getView());
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

    @Override
    public void onSmsErrorResponse(View view,String message) {
        Toast.makeText(view.getContext(),message + "error occurred",Toast.LENGTH_LONG).show();
    }
}