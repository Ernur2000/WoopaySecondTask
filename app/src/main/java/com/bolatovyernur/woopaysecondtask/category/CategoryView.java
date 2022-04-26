package com.bolatovyernur.woopaysecondtask.category;

import android.view.View;

import com.bolatovyernur.woopaysecondtask.db.Category;
import com.bolatovyernur.woopaysecondtask.db.Service;
import com.bolatovyernur.woopaysecondtask.model.CategoryResponse;
import com.bolatovyernur.woopaysecondtask.model.TopServiceResponse;

import java.util.List;

public interface CategoryView {
    void putTopServiceDataToRecyclerView(List<TopServiceResponse> service, View view);
    void putCategoryDataToRecyclerView(List<Category> categories, View view);
}
