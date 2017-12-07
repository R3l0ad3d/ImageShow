package com.example.user.imageshow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;

    public static int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageItem);
        recyclerView = findViewById(R.id.rvImages);

        manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.hasFixedSize();

        AdapterRec adapter = new AdapterRec(this,imageView,recyclerView);

        recyclerView.setAdapter(adapter);
    }
}
