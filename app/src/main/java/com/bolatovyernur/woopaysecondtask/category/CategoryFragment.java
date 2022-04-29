package com.bolatovyernur.woopaysecondtask.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
import com.bolatovyernur.woopaysecondtask.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryView {
    FragmentCategoryBinding binding;
    LinearLayoutManager layoutManagerHorizontal, layoutManagerVertical;
    CategoryPresenter categoryPresenter;
    ProgressBar progressBar;
    PreferenceUtils preferenceUtils;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceUtils = new PreferenceUtils(getContext());
        layoutManagerHorizontal = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManagerVertical = new LinearLayoutManager(getContext());
        binding.rvTopService.setLayoutManager(layoutManagerHorizontal);
        binding.rvCategory.setLayoutManager(layoutManagerVertical);
        binding.rvCategory.setHasFixedSize(true);
        binding.rvTopService.setHasFixedSize(true);

        categoryPresenter = new CategoryPresenter(this);
        progressBar = binding.progressBar;
        progressBar.setVisibility(View.VISIBLE);
        categoryPresenter.getTopServices(view);
        categoryPresenter.readServiceCategory(view);
        binding.logOut.setOnClickListener(view1 -> {
            preferenceUtils.deleteAllData();
            Navigation.findNavController(view).navigate(R.id.action_categoryFragment_to_loginFragment);
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d("BackButton", "Back button clicks");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }


    @Override
    public void putTopServiceDataToRecyclerView(List<TopServiceResponse> service, View view) {
        TopServiceAdapter adapter = new TopServiceAdapter((ArrayList<TopServiceResponse>) service, view.getContext());
        binding.rvTopService.setAdapter(adapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void putCategoryDataToRecyclerView(List<Category> categories, View view) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(view.getContext(), (ArrayList<Category>) categories);
        binding.rvCategory.setAdapter(categoryAdapter);
    }
}