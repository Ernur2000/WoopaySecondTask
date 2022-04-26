package com.bolatovyernur.woopaysecondtask.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.databinding.FragmentCategoryBinding;
import com.bolatovyernur.woopaysecondtask.db.Category;
import com.bolatovyernur.woopaysecondtask.model.TopServiceResponse;
import com.bolatovyernur.woopaysecondtask.util.Constants;
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;
import com.google.gson.JsonSyntaxException;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryView {
    FragmentCategoryBinding binding;
    LinearLayoutManager layoutManagerHorizontal, layoutManagerVertical;
    CategoryPresenter categoryPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutManagerHorizontal = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManagerVertical = new LinearLayoutManager(getContext());
        binding.rvTopService.setLayoutManager(layoutManagerHorizontal);
        binding.rvCategory.setLayoutManager(layoutManagerVertical);
        binding.rvCategory.setHasFixedSize(true);
        binding.rvTopService.setHasFixedSize(true);

        categoryPresenter = new CategoryPresenter(this);
        String login = PreferenceUtils.getString(Constants.KEY_EMAIL);
        String token = PreferenceUtils.getString(Constants.KEY_TOKEN);
        try {
            String messageAfterDecrypt = AESCrypt.decrypt(login, token);
            categoryPresenter.getTopServices(view, messageAfterDecrypt,binding.progressBar);
        } catch (IllegalStateException | JsonSyntaxException | GeneralSecurityException exception) {
            Toast.makeText(view.getContext(), "Error" + exception.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        categoryPresenter.readServiceCategory(view);
        binding.logOut.setOnClickListener(view1 -> {
            PreferenceUtils.deleteData();
            Navigation.findNavController(view).navigate(R.id.action_categoryFragment_to_loginFragment);
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d("BACKBUTTON", "Back button clicks");
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);
    }

    @Override
    public void putTopServiceDataToRecyclerView(List<TopServiceResponse> service, View view) {
        TopServiceAdapter adapter = new TopServiceAdapter((ArrayList<TopServiceResponse>) service, view.getContext());
        binding.rvTopService.setAdapter(adapter);
    }

    @Override
    public void putCategoryDataToRecyclerView(List<Category> categories, View view) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(view.getContext(), (ArrayList<Category>) categories);
        binding.rvCategory.setAdapter(categoryAdapter);
    }
}