package com.bolatovyernur.woopaysecondtask.mainPage;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentMainPageBinding;
import com.bolatovyernur.woopaysecondtask.util.Constants;
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class MainPageFragment extends Fragment{
    FragmentMainPageBinding binding;
    PreferenceUtils preferenceUtils;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainPageBinding.inflate(inflater,container,false);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceUtils = new PreferenceUtils(getContext());
        String login = preferenceUtils.getString(Constants.KEY_EMAIL);
        String password = preferenceUtils.getString(Constants.KEY_PASSWORD);
        if (login!=null && password!=null){
            try {
                String messageAfterDecrypt = AESCrypt.decrypt(login, password);
                Log.d("DecryptedMSG",messageAfterDecrypt);
                binding.tvLogin.setText(login);
                binding.tvPassword.setText(messageAfterDecrypt);
            }catch (GeneralSecurityException e){
                Toast.makeText(getContext(),"Incorrect password",Toast.LENGTH_LONG).show();
            }
        }
        binding.btnLogout.setOnClickListener(view1 -> {
            preferenceUtils.deleteData();
            Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_registrationFragment);
        });
    }
}