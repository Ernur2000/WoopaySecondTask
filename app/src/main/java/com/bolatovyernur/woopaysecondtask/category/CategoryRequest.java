package com.bolatovyernur.woopaysecondtask.category;

public class CategoryRequest {
    private String categoryName;
    private String ImageURL;

    public CategoryRequest(String categoryName, String imageURL) {
        this.categoryName = categoryName;
        ImageURL = imageURL;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
