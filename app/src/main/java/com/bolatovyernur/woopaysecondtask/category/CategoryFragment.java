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
import com.bolatovyernur.woopaysecondtask.model.CategoryResponse;
import com.bolatovyernur.woopaysecondtask.model.TopServiceResponse;
import com.bolatovyernur.woopaysecondtask.util.Constants;
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryView {
    FragmentCategoryBinding binding;
    LinearLayoutManager layoutManagerHorizontal, layoutManagerVertical;
    CategoryPresenter categoryPresenter;
    private ArrayList<TopServiceResponse> topServiceResponses = new ArrayList<>();

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

        String token = PreferenceUtils.getString(Constants.KEY_TOKEN);
        String login = PreferenceUtils.getString(Constants.KEY_EMAIL);
        try {
            String messageAfterDecrypt = AESCrypt.decrypt(login, token);
            Log.d("DecryptedMSG", messageAfterDecrypt);
            categoryPresenter.getTopService(messageAfterDecrypt, getView());
        } catch (GeneralSecurityException e) {
            Toast.makeText(getContext(), "Incorrect password", Toast.LENGTH_LONG).show();
        }
        //categoryPresenter.getCategories(getView(),binding.progressBar);
        categoryPresenter.readDB(view);
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
    public void putDataIntoRecyclerView(List<TopServiceResponse> topServiceResponses, View view) {
        TopServiceAdapter adapter = new TopServiceAdapter((ArrayList<TopServiceResponse>) topServiceResponses);
        binding.rvTopService.setAdapter(adapter);
    }

    @Override
    public void putCategoryDataIntoRecyclerView(List<CategoryResponse> categoryResponses, View view) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(view.getContext(), (ArrayList<CategoryResponse>) categoryResponses);
        binding.rvCategory.setAdapter(categoryAdapter);
    }
}