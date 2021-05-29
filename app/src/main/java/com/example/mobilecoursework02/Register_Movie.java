package com.example.mobilecoursework02;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register_Movie extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4, editText5, editText6;
    DatabaseHelper db;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__movie);

        db = new DatabaseHelper(this);

        editText1 = findViewById(R.id.title_edit_txt);
        editText2 = findViewById(R.id.year_edit_txt);
        editText3 = findViewById(R.id.director_edit_txt);
        editText4 = findViewById(R.id.name_list_edit_txt);
        editText5 = findViewById(R.id.rating_edit_txt);
        editText6 = findViewById(R.id.review_edit_txt);

        button = findViewById(R.id.save_btn);



    }


    public void save(View view){
        boolean insert =  db.insert_movies(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString(),Integer.parseInt(String.valueOf(editText5.getText())),editText6.getText().toString());

        Cursor movies = db.get_movies();
        if(insert){
            Log.d("xyz", String.valueOf(movies.getCount()));
            Log.d("xyz", "Successfully Added the Movie");
            editText1.setText(" ");
        }


    }


}