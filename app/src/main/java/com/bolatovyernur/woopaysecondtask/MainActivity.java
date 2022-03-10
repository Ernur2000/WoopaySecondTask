package com.bolatovyernur.woopaysecondtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bolatovyernur.woopaysecondtask.authentication.AuthFragment;
import com.bolatovyernur.woopaysecondtask.category.CategoryFragment;
import com.bolatovyernur.woopaysecondtask.databinding.ActivityMainBinding;
import com.bolatovyernur.woopaysecondtask.registration.RegistrationFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final AuthFragment authFragment = new AuthFragment();
    private final RegistrationFragment registrationFragment = new RegistrationFragment();
    private final CategoryFragment categoryFragment = new CategoryFragment();
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        replaceFragment(authFragment);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.it_auth:
                    replaceFragment(authFragment);
                    return true;
                case R.id.it_reg:
                    replaceFragment(registrationFragment);
                    return true;
                case R.id.it_category:
                    replaceFragment(categoryFragment);
                    return true;
            }
            return false;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }
}