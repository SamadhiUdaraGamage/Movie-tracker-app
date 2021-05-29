package com.example.mobilecoursework02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button) findViewById(R.id.Reg_Movie);
        btn2=(Button) findViewById(R.id.display_btn);
        btn3=(Button) findViewById(R.id.Favourites_btn);
        btn4=(Button) findViewById(R.id.edit_btn);
        btn5=(Button) findViewById(R.id.search_btn);
        btn6=(Button) findViewById(R.id.ratings_btn);

        //register movie button event handler
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register_Movie();
                
            }
        });

        //display movie button event handler
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Display_Movies();
            }
        });

        //favourite movie button event handler
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Favourites();
            }
        });

        //edit movies movie button event handler
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit_Movies();
            }
        });

        //search movie button event handler
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search();
            }
        });

        //rating movie button event handler
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ratings();
            }
        });
    }

    //go to register movie intent
    public void Register_Movie(){
        Intent intent = new Intent(this, Register_Movie.class);
        startActivity(intent);

    }

    //go to display movie intent
    public void Display_Movies(){
        Intent intent = new Intent(this, Display_Movies.class);
        startActivity(intent);
    }

    //go to favourite intent
    public void Favourites(){
        Intent intent = new Intent(this, Favourites.class);
        startActivity(intent);
    }

    //go to edit intent
    public void Edit_Movies(){
        Intent intent = new Intent(this, Edit_Movies.class);
        startActivity(intent);
    }

    //go to search intent
    public void Search(){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    //go to ratings intent
    public void Ratings(){
        Intent intent = new Intent(this, Ratings.class);
        startActivity(intent);
    }
}