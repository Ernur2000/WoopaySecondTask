package com.bolatovyernur.woopaysecondtask.registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentRegistrationBinding;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentSmsBinding;

public class SmsFragment extends Fragment {
    FragmentSmsBinding fragmentSmsBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSmsBinding = FragmentSmsBinding.inflate(inflater,container,false);
        return fragmentSmsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentSmsBinding.back.setOnClickListener(view1 -> {
            Navigation.findNavController(view1).
                    navigate(R.id.action_smsFragment_to_registrationFragment);
        });
        fragmentSmsBinding.btnNext.setOnClickListener(view1 -> {
            Navigation.findNavController(view1).navigate(R.id.action_smsFragment_to_passwordFragment);
        });
    }
}