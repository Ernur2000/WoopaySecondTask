package com.bolatovyernur.woopaysecondtask.registration.PinCodePage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentPinCodePageBinding;
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class PinCodePageFragment extends Fragment {
    private FragmentPinCodePageBinding binding;
    private final ArrayList<String> numbers_list = new ArrayList<>();
    private PreferenceUtils preferenceUtils;
    private String passCode = "";
    private String num_01, num_02, num_03, num_04;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPinCodePageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceUtils = new PreferenceUtils(getContext());
        if (PreferenceUtils.getString("passCode")!=null){
            binding.textPinCode.setText("Введите пин-код для быстрого доступа к приложению");
        }else {
            binding.textPinCode.setText("Установите пин-код для быстрого доступа к приложению");
        }
        BiometricManager biometricManager = BiometricManager.from(view.getContext());
        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(getContext(), "Device doesn't have a fingerPrint", Toast.LENGTH_LONG).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(getContext(), "Not working", Toast.LENGTH_LONG).show();
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(getContext(), "No fingerPrint assigned", Toast.LENGTH_LONG).show();

        }
        Executor executor = ContextCompat.getMainExecutor(view.getContext());
        biometricPrompt = new BiometricPrompt(requireActivity(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getContext(), "Login Success", Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.action_pinCodePageFragment_to_categoryFragment);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Здравстсвуйте")
                .setDescription("Use FingerPrint to Login").setNegativeButtonText("Отмена").build();
        setOnClickListener();
    }

    private void setOnClickListener() {
        binding.forgotPass.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_pinCodePageFragment_to_loginFragment));
        binding.number1.setOnClickListener(view1 -> {
            numbers_list.add("1");
            passNumber(numbers_list);
        });
        binding.number2.setOnClickListener(view1 -> {
            numbers_list.add("2");
            passNumber(numbers_list);
        });
        binding.number3.setOnClickListener(view1 -> {
            numbers_list.add("3");
            passNumber(numbers_list);
        });
        binding.number4.setOnClickListener(view1 -> {
            numbers_list.add("4");
            passNumber(numbers_list);
        });
        binding.number5.setOnClickListener(view1 -> {
            numbers_list.add("5");
            passNumber(numbers_list);
        });
        binding.number6.setOnClickListener(view1 -> {
            numbers_list.add("6");
            passNumber(numbers_list);
        });
        binding.number7.setOnClickListener(view1 -> {
            numbers_list.add("7");
            passNumber(numbers_list);
        });
        binding.number8.setOnClickListener(view1 -> {
            numbers_list.add("8");
            passNumber(numbers_list);
        });
        binding.number9.setOnClickListener(view1 -> {
            numbers_list.add("9");
            passNumber(numbers_list);
        });
        binding.number0.setOnClickListener(view1 -> {
            numbers_list.add("0");
            passNumber(numbers_list);
        });
        binding.icDelete.setOnClickListener(view1 -> {
            if (numbers_list.size()==0) return;
            numbers_list.remove(numbers_list.size() - 1);
            passNumber(numbers_list);
        });
        binding.icTouchId.setOnClickListener(view -> biometricPrompt.authenticate(promptInfo));
    }

    private void passNumber(ArrayList<String> numbers_list) {
        binding.line1.setBackgroundResource(R.drawable.ic_default_line_grey_2);
        binding.line2.setBackgroundResource(R.drawable.ic_default_line_grey_2);
        binding.line3.setBackgroundResource(R.drawable.ic_default_line_grey_2);
        binding.line4.setBackgroundResource(R.drawable.ic_default_line_grey_2);
        switch (numbers_list.size()) {
            case 1:
                num_01 = numbers_list.get(0);
                binding.line1.setBackgroundResource(R.drawable.bg_line);
                break;
            case 2:
                num_02 = numbers_list.get(1);
                binding.line2.setBackgroundResource(R.drawable.bg_line);
                binding.line1.setBackgroundResource(R.drawable.bg_line);
                break;
            case 3:
                num_03 = numbers_list.get(2);
                binding.line2.setBackgroundResource(R.drawable.bg_line);
                binding.line1.setBackgroundResource(R.drawable.bg_line);
                binding.line3.setBackgroundResource(R.drawable.bg_line);
                break;
            case 4:
                num_04 = numbers_list.get(3);
                binding.line2.setBackgroundResource(R.drawable.bg_line);
                binding.line1.setBackgroundResource(R.drawable.bg_line);
                binding.line3.setBackgroundResource(R.drawable.bg_line);
                binding.line4.setBackgroundResource(R.drawable.bg_line);
                passCode = num_01 + num_02 + num_03 + num_04;
                Log.d("Log", String.valueOf(numbers_list));
                if (PreferenceUtils.getString("passCode") == null) {
                    PreferenceUtils.saveString("passCode", passCode);
                    numbers_list.clear();
                    binding.line1.setBackgroundResource(R.drawable.ic_default_line_grey_2);
                    binding.line2.setBackgroundResource(R.drawable.ic_default_line_grey_2);
                    binding.line3.setBackgroundResource(R.drawable.ic_default_line_grey_2);
                    binding.line4.setBackgroundResource(R.drawable.ic_default_line_grey_2);
                    binding.textPinCode.setText("Повторите пин-код");
                } else {
                    matchPassCode();
                }
                break;
        }

    }

    private void matchPassCode() {
        if (PreferenceUtils.getString("passCode").equals(passCode)) {
            Navigation.findNavController(getView()).navigate(R.id.action_pinCodePageFragment_to_categoryFragment);
        } else {
            Toast.makeText(getContext(), "Passcode doesn't match please retry again!", Toast.LENGTH_LONG).show();
            numbers_list.clear();
            binding.line1.setBackgroundResource(R.drawable.ic_default_line_grey_2);
            binding.line2.setBackgroundResource(R.drawable.ic_default_line_grey_2);
            binding.line3.setBackgroundResource(R.drawable.ic_default_line_grey_2);
            binding.line4.setBackgroundResource(R.drawable.ic_default_line_grey_2);
        }
    }
}