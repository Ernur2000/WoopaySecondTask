package com.bolatovyernur.woopaysecondtask.pinCodePage;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
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
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.concurrent.Executor;

public class PinCodePageFragment extends Fragment implements PinCodePageView {
    private FragmentPinCodePageBinding binding;
    private final ArrayList<String> numbers_list = new ArrayList<>();
    private String passCode = "";
    private String num_01;
    private String num_02;
    private String num_03;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private PinCodePagePresenter presenter;
    private int numberOfRemainingLoginAttempts = 3;
    private String decrypt;
    private BiometricManager biometricManager;
    private PreferenceUtils preferenceUtils;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPinCodePageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setBiometricPrompt();
        setOnClickListener();
        setVisibilities();
    }
    private void init(){
        presenter = new PinCodePagePresenter();
        preferenceUtils = new PreferenceUtils(getContext());
        biometricManager = BiometricManager.from(requireContext());
    }
    private void setBiometricPrompt(){
        int state = biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK);
        if (state == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE) {
            Toast.makeText(getContext(), "Device doesn't have a fingerPrint", Toast.LENGTH_LONG).show();
            binding.icTouchId.setVisibility(View.INVISIBLE);
        } else if (state == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE) {
            Toast.makeText(getContext(), "Not working", Toast.LENGTH_LONG).show();
            binding.icTouchId.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), "No fingerPrint assigned", Toast.LENGTH_LONG).show();
            binding.icTouchId.setVisibility(View.INVISIBLE);
        } else if (state == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) {
            Toast.makeText(getContext(), "No fingerPrint assigned", Toast.LENGTH_LONG).show();
            binding.icTouchId.setVisibility(View.INVISIBLE);
        }
        Executor executor = ContextCompat.getMainExecutor(requireContext());
        biometricPrompt = new BiometricPrompt(requireActivity(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getContext(), "Login Success", Toast.LENGTH_LONG).show();
                Navigation.findNavController(requireView()).navigate(R.id.action_pinCodePageFragment_to_categoryFragment);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Здравстсвуйте")
                .setDescription("Use FingerPrint to Login").setNegativeButtonText("Отмена").build();
    }

    private void setVisibilities(){
        String answer = preferenceUtils.getString("Answer");
        if (answer != null && answer.equals("yes")) {
            binding.icTouchId.setVisibility(View.VISIBLE);
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString("flag").equals("enter")) {
                binding.forgotPass.setVisibility(View.VISIBLE);
                binding.tvGreeting.setText("Введите пин-код для быстрого доступа к приложению");
            } else {
                binding.forgotPass.setVisibility(View.INVISIBLE);
                binding.tvGreeting.setText("Установите пин-код для быстрого доступа к приложению");
            }
        }
    }
    private void setOnClickListener() {
        binding.icTouchId.setOnClickListener(view -> biometricPrompt.authenticate(promptInfo));
        binding.forgotPass.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_pinCodePageFragment_to_loginFragment);
            preferenceUtils.deleteData("passCode");
        });
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
            if (numbers_list.size() == 0) return;
            numbers_list.remove(numbers_list.size() - 1);
            passNumber(numbers_list);
        });
    }

    private void passNumber(ArrayList<String> numbers_list) {
        setBackgroundToGrey2();
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
                String num_04 = numbers_list.get(3);
                binding.line2.setBackgroundResource(R.drawable.bg_line);
                binding.line1.setBackgroundResource(R.drawable.bg_line);
                binding.line3.setBackgroundResource(R.drawable.bg_line);
                binding.line4.setBackgroundResource(R.drawable.bg_line);
                passCode = num_01 + num_02 + num_03 + num_04;
                if (preferenceUtils.getString("passCode") == null) {
                    try {
                        String encryptedMessage = AESCrypt.encrypt("passCode", passCode);
                        preferenceUtils.saveString("passCode", encryptedMessage);
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                    numbers_list.clear();
                    setBackgroundToGrey2();
                    binding.tvGreeting.setText("Повторите пин-код");
                } else {
                    matchPassCode();
                }
                break;
        }

    }

    private void setBackgroundToGrey2() {
        binding.line1.setBackgroundResource(R.drawable.ic_default_line_grey_2);
        binding.line2.setBackgroundResource(R.drawable.ic_default_line_grey_2);
        binding.line3.setBackgroundResource(R.drawable.ic_default_line_grey_2);
        binding.line4.setBackgroundResource(R.drawable.ic_default_line_grey_2);
    }

    private void login() {
        presenter.login(requireView());
    }

    private void matchPassCode() {
        try {
            String pin = preferenceUtils.getString("passCode");
            decrypt = AESCrypt.decrypt("passCode", pin);
            Log.d("Message", decrypt);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        if (decrypt.equals(passCode)) {
            String ans = preferenceUtils.getString("Answer");
            if (ans != null) {
                login();
            } else {
                if (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
                        == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE || biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
                        == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE || biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
                        == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) {
                    binding.icTouchId.setVisibility(View.INVISIBLE);
                    login();
                } else {
                    AlertDialog();
                }
            }
        } else {
            Vibrator vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
            if (binding.tvGreeting.getText() == "Повторите пин-код") {
                binding.textPinCode.setText("Код не совпадает");
            } else {
                final Handler handler = new Handler();
                @SuppressLint("SetTextI18n") Runnable runnable = () -> {
                    if (numberOfRemainingLoginAttempts == 2) {
                        binding.textPinCode.setText("Осталось " + numberOfRemainingLoginAttempts + " попытки");
                    } else {
                        binding.textPinCode.setText("Осталось " + numberOfRemainingLoginAttempts + " попытка");
                    }
                    handler.removeMessages(0);
                };
                handler.postDelayed(runnable, 1000);
                binding.textPinCode.setText("Неверный код");
                numberOfRemainingLoginAttempts--;
            }
            numbers_list.clear();
            binding.line1.setBackgroundResource(R.drawable.ic_default_line_grey_2);
            binding.line2.setBackgroundResource(R.drawable.ic_default_line_grey_2);
            binding.line3.setBackgroundResource(R.drawable.ic_default_line_grey_2);
            binding.line4.setBackgroundResource(R.drawable.ic_default_line_grey_2);
        }
        if (numberOfRemainingLoginAttempts == 0) {
            Navigation.findNavController(requireView()).navigate(R.id.action_pinCodePageFragment_to_loginFragment);
            preferenceUtils.deleteData("passCode");
        }
    }

    private void AlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Безопасность");
        alertDialog.setMessage("Использовать отпечаток пальца для входа");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Да", (dialogInterface, i) -> {
            preferenceUtils.saveString("Answer", "yes");
            login();
            binding.icTouchId.setVisibility(View.VISIBLE);
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Отмена", (dialogInterface, i) -> {
            preferenceUtils.saveString("Answer", "no");
            login();
        });
        alertDialog.show();
    }

    @Override
    public void onSuccessResponse(View view) {
        Navigation.findNavController(view).navigate(R.id.action_pinCodePageFragment_to_categoryFragment);
    }
}