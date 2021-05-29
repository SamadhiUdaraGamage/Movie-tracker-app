package com.example.mobilecoursework02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

public class Edit extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4,  editText6;
    DatabaseHelper db;
    String id;
    RatingBar ratingBar;


    @SuppressLint("SetTextI18n")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        db = new DatabaseHelper(this);
        id = getIntent().getStringExtra("id");
        Log.d("myz" , "Edit ID "+id);

        Cursor res = db.get_movie_edit(id);
        Log.d("myz" , "Edit ID "+res.getCount());
        editText1 = findViewById(R.id.title_edit_txt);
        editText2 = findViewById(R.id.year_edit_txt);
        editText3 = findViewById(R.id.director_edit_txt);
        editText4 = findViewById(R.id.name_list_edit_txt);
        ratingBar =findViewById(R.id.ratingBar);
        editText6 = findViewById(R.id.review_edit_txt);

        if( res != null && res.moveToFirst() ) {
            String movies_title = res.getString(res.getColumnIndex("title"));
            String movies_year = res.getString(res.getColumnIndex("year"));
            String movies_director = res.getString(res.getColumnIndex("director"));
            String movies_actors = res.getString(res.getColumnIndex("actors"));
            String movies_rating = res.getString(res.getColumnIndex("rating"));
            String movies_review = res.getString(res.getColumnIndex("review"));
            Log.d("myz" , "Edit ID "+movies_review);

            editText1.setText(movies_title);
            editText2.setText(movies_year);
            editText3.setText(movies_director);
            editText4.setText(movies_actors);
            ratingBar.setRating(Float.parseFloat(movies_rating));
            editText6.setText(movies_review);

        }



    }

    //update data
    // watched https://youtu.be/AGdudT1iUI8
    public void edit(View view){
        boolean insert =  db.edit_movie(id,editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString(),(int)ratingBar.getRating(),editText6.getText().toString());
        Cursor movies = db.get_movies();

    }
}