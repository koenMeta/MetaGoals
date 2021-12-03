package com.training.MetaGoals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.training.MetaGoals.create_challenge.CreateChallenge;
import com.training.MetaGoals.qr_code.QrCodeActicity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<com.training.MetaGoals.Challenge> challenges;

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        challenges = new ArrayList<>();
        requestQueue = com.training.MetaGoals.VolleySingleton.getmInstance(this).getRequestQueue();

        getImages();
        configureNewChallengeBtn();
    }

    private void getImages()  {
        String url = "https://mocki.io/v1/b9b6477b-e2b9-4023-9a2b-2433874729d7 ";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for( int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String challenge = jsonObject.getString("title");
                        String overview = jsonObject.getString("overview");
                        String challengeUrl = jsonObject.getString("poster");
//                        Double rating = jsonObject.getDouble("rating");

                        com.training.MetaGoals.Challenge card = new com.training.MetaGoals.Challenge(challenge, challengeUrl, overview);
                        challenges.add(card);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    com.training.MetaGoals.RecyclerViewAdapter adapter = new com.training.MetaGoals.RecyclerViewAdapter(MainActivity.this, challenges);

                    recyclerView.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void  initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        com.training.MetaGoals.RecyclerViewAdapter adapter = new com.training.MetaGoals.RecyclerViewAdapter(this, challenges);
        recyclerView.setAdapter(adapter);
    };


    private void configureNewChallengeBtn() {
        RelativeLayout newChallengeBtn = findViewById(R.id.layout_new_challenge);
        newChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to create challenge
                Intent newChallengeIntent = new Intent(getApplicationContext(), CreateChallenge.class);
                startActivity(newChallengeIntent);
            }
        });
    }
}