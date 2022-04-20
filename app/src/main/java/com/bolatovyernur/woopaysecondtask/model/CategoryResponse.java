package com.bolatovyernur.woopaysecondtask.model;

public class CategoryResponse {
    private int id;
    private int parent_id;
    private String title;
    private String name;
    private String picture;
    private String parent;
    private String children;
    private String blacklist;
    private String language;
    private String description;
    private int position;
    private String picture_url;

    public CategoryResponse(int id, int parent_id, String title, String name, String picture, String parent, String children, String blacklist, String language, String description, int position, String picture_url) {
        this.id = id;
        this.parent_id = parent_id;
        this.title = title;
        this.name = name;
        this.picture = picture;
        this.parent = parent;
        this.children = children;
        this.blacklist = blacklist;
        this.language = language;
        this.description = description;
        this.position = position;
        this.picture_url = picture_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(String blacklist) {
        this.blacklist = blacklist;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}
