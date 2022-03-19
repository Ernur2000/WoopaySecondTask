package com.bolatovyernur.woopaysecondtask.registration;

import static android.graphics.Color.rgb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentRegistrationBinding;

public class RegistrationFragment extends Fragment {
    FragmentRegistrationBinding fragmentRegistrationBinding;
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
        fragmentRegistrationBinding.btnNext.setOnClickListener(view1 ->
                Navigation.findNavController(view1).
                        navigate(R.id.action_registrationFragment_to_smsFragment));
        fragmentRegistrationBinding.checkbox.setOnClickListener(view1 -> {
            if(fragmentRegistrationBinding.checkbox.isChecked()) {
                fragmentRegistrationBinding.btnNext.setEnabled(true);

            }
            else {
                fragmentRegistrationBinding.btnNext.setEnabled(false);
            }
        });
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

}