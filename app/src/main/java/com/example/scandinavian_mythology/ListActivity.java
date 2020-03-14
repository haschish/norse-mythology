package com.example.scandinavian_mythology;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    public static final String EXTRA_FILENAME = "filename";
    ArrayList<Article> list = new ArrayList<>();
    ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String fileName = intent.getExtras().getString(EXTRA_FILENAME);


//        Article odin = new Article("Один", "верховный бог в германо-скандинавской мифологии");
//        Article tor = new Article("Тор", "один из асов, бог грома и бури, защищающий богов и людей от великанов и чудовищ");
//
//        list.add(odin);
//        list.add(tor);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new ArticleAdapter(this, list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        loadJson(fileName);
    }

    public void showArticle(int adapterPosition) {
        Article article = list.get(adapterPosition);
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra(WebActivity.EXTRA_ID, article.getId());
        startActivity(intent);
    }

    private void loadJson(String fileName) {
        AssetManager am = getAssets();
        BufferedReader reader = null;
        try (InputStream in = am.open(fileName)) {

            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(new Article(jsonArray.getJSONObject(i)));
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.e("Error loading json: ", "", e);
        }
    }
}
