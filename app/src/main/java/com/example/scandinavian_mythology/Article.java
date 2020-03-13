package com.example.scandinavian_mythology;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

public class Article {
    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_FILENAME = "filename";
    private String name;
    private String description;
    private String fileName;
    private String type;

    public Article(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Article(JSONObject jo) throws JSONException {
        name = jo.getString(JSON_TITLE);
        description = jo.getString(JSON_DESCRIPTION);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFileName() {
        return fileName;
    }

    public String getType() {
        return type;
    }
}
