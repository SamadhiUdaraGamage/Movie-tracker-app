package com.example.mobilecoursework02;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movies_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists movies" + " (movie_id INTEGER PRIMARY KEY AUTOINCREMENT,title text, year text,director text,actors text,rating INTEGER,review text,favourite int default 0)");



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movies");
        onCreate(db);

    }

    //add insert data
    public boolean insert_movies(String title, String year, String director, String actors, int rating, String review) {

        SQLiteDatabase db = this.getWritableDatabase();

        //   db.insert("paper_complete",null,contentValues);
        String insert_movie = "INSERT INTO movies (title, year, director,actors,rating,review) VALUES (\"" + title + "\",\"" + year + "\",\"" + director + "\",\"" + actors + "\",\"" + rating + "\",\"" + review + "\")";
        db.execSQL(insert_movie);
        return true;
    }

    //add get movies
    public Cursor get_movies(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies  order by title asc",null);
        return res;
    }

    //add get favourite movies
    public Cursor get_fav_movies(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies where favourite = "+1,null);
        return res;
    }

    //add get movie edit
    public Cursor get_movie_edit(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies where movie_id = "+ Integer.parseInt(id),null);
        return res;
    }

    //add search movies
    public Cursor search_movies(String key) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies where title like '%"+key+"%' OR title like '"+key+"%' OR title like '%"+key+"' OR title like '"+key+"'" +
                " OR director like '%"+key+"%' OR director like '"+key+"%' OR director like '%"+key+"' OR director like '"+key+"'",null);

        return res;
    }

    //set favourite movies
    public void set_fav(int[] movie_fav,String[] movie_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("xyz", "Button Clicked and come to here");

        for(int a=0;a<movie_fav.length;a++) {
            try {
                if(movie_fav[a] == 0) {
                    db.execSQL("UPDATE movies SET favourite = 0 where movie_id = " + Integer.parseInt(movie_id[a]));
                }else{
                    db.execSQL("UPDATE movies SET favourite = 1 where movie_id = " + Integer.parseInt(movie_id[a]));
                }
            } catch (SQLException e) {
                Log.d("xyz", "go go go");
            }
        }
        Log.d("xyz", "Runninng Succedded");

    }

    // edit movies
    public boolean edit_movie(String id, String title, String year, String director, String actors, int rating, String review) {
        Log.d("myz", "ID "+id);

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE movies SET title = \""+title+"\",  year = \""+year+"\",  director = \""+director+"\",  actors = \""+actors+"\",  rating = \""+rating+"\",  review = \""+review+"\" where movie_id = " + Integer.parseInt(id));
        //   db.insert("paper_complete",null,contentValues);
//        String insert_movie = "INSERT INTO movies (title, year, director,actors,rating,review) VALUES (\"" + title + "\",\"" + year + "\",\"" + director + "\",\"" + actors + "\",\"" + rating + "\",\"" + review + "\")";
        Log.d("myz", "Not Error: Update");
        return true;
    }
}
