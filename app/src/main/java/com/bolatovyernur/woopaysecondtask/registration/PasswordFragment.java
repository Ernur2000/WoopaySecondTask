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
import com.bolatovyernur.woopaysecondtask.databinding.FragmentPasswordBinding;

public class PasswordFragment extends Fragment {
    FragmentPasswordBinding fragmentPasswordBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPasswordBinding = FragmentPasswordBinding.inflate(inflater,container,false);
        return fragmentPasswordBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentPasswordBinding.back.setOnClickListener(view1 -> {
            Navigation.findNavController(view1).navigate(R.id.action_passwordFragment_to_smsFragment);
        });
    }
}