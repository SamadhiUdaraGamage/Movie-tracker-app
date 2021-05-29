package com.example.mobilecoursework02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TotalRating extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<String> movie_names, movie_ids;
    TextView no_movies;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager imdblistlayout;
    IMDBResultAdapter adapter;
    String id;
    String image_url;
    String title;
    TextView rating, title_txt;
    static ImageView image;
    DownloadImageTask downloadImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_rating);

        title = getIntent().getStringExtra("title");
        image_url = getIntent().getStringExtra("url");
        id = getIntent().getStringExtra("id");

        title_txt = findViewById(R.id.title);
        image = findViewById(R.id.imageView);
        rating = findViewById(R.id.rating);

        title_txt.setText(title);

//        recyclerView =  findViewById(R.id.result_view);
        subjectload();
        imageload();
    }

    private void imageload() {

            downloadImage=new DownloadImageTask();
            downloadImage.execute(image_url);


    }

    public void subjectload() {

        String movie_url = "https://imdb-api.com/en/API/UserRatings/k_y5fcp7hu/" + id;

        final StringRequest jar = new StringRequest(Request.Method.GET, movie_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("myz", "Response : " + response);
//                        image.setImageBitmap(getBitmapFromURL(image_url));
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String total_rating = jsonObject.getString("totalRating");
                            rating.setText("Total Rating : "+total_rating);
                            Log.d("xyz" , "no error :");
                        } catch (JSONException e) {
                            Toast.makeText(TotalRating.this, "Something Wrong, Go Back and try again", Toast.LENGTH_LONG).show();
                            Log.d("xyz" , "json error :" + e.getMessage() );
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("aa" , "errer "+ error);
                Log.d("myz", "Error: " + error);

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        MySingleton.getInstance().addRequest(jar);
        //  paperload = true;
    }


}