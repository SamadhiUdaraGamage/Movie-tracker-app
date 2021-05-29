package com.example.mobilecoursework02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Search extends AppCompatActivity {

    DatabaseHelper db;
    EditText keyText;
    String[] movies_title,movies_id;
    int[] movies_fav;
    TextView no_movies;
    RecyclerView recyclerView;
    Adapter_Movie_Search adapter;
    RecyclerView.LayoutManager movielistlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        no_movies = findViewById(R.id.no_movies);
        recyclerView =  findViewById(R.id.result_view);
        db = new DatabaseHelper(this);
        keyText = findViewById(R.id.key);
    }

    public void lookup(View view){
        String key = keyText.getText().toString();

        Cursor res = db.search_movies(key);

        Log.d("myz", "Number of movies  lookup"+res.getCount());
        if(res.getCount() != 0) {
            movies_title = new String[res.getCount()];
            movies_id = new String[res.getCount()];
            movies_fav = new int[res.getCount()];
            int count1 = 0;
            while (res.moveToNext()) {
                movies_title[count1] = res.getString(res.getColumnIndex("title"));
                movies_id[count1] = res.getString(res.getColumnIndex("movie_id"));
                movies_fav[count1] = res.getInt(res.getColumnIndex("favourite"));
                Log.d("myz", "director lookup "+res.getString(res.getColumnIndex("director")));

                count1++;
            }
            recycler();
        }else{
            Log.d("myz", "No Movies");
            recyclerView.setVisibility(View.GONE);
            no_movies.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("WrongConstant")
    public void recycler() {
        movielistlayout = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(movielistlayout);
        adapter = new Adapter_Movie_Search(this,movies_title,movies_id);
//        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }
}