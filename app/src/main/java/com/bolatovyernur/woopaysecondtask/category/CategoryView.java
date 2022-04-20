package com.bolatovyernur.woopaysecondtask.category;

import android.view.View;

import com.bolatovyernur.woopaysecondtask.model.CategoryResponse;
import com.bolatovyernur.woopaysecondtask.model.TopServiceResponse;

import java.util.List;

public interface CategoryView {
    void putDataIntoRecyclerView(List<TopServiceResponse> topServiceResponses, View view);
    void putCategoryDataIntoRecyclerView(List<CategoryResponse> categoryResponses, View view);
}
