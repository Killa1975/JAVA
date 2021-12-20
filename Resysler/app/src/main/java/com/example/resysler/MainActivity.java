package com.example.resysler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Model> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();

        RecyclerView recyclerView = findViewById(R.id.recycleView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        StateAdapter adapter = new StateAdapter(this, movies);
        recyclerView.setAdapter(adapter);
    }

    private void setData(){
        movies.add(new Model(R.drawable.r, "Россия"));
        movies.add(new Model(R.drawable.g, "Германия"));
        movies.add(new Model(R.drawable.v, "Великобритания"));
    }

}