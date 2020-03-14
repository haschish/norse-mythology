package com.example.scandinavian_mythology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.division_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fileName;
                switch (position) {
                    case 0: fileName = "gods.json"; break;
                    case 1: fileName = "goddesses.json"; break;
                    default: fileName = "";
                }
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra(ListActivity.EXTRA_FILENAME, fileName);
                startActivity(intent);
            }
        });


    }
}
