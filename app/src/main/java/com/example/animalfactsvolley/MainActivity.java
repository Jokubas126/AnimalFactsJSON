package com.example.animalfactsvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.*;

public class MainActivity extends AppCompatActivity {

    String objectRequestURL = "https://cat-fact.herokuapp.com/facts/random";
    String arrayRequestURL = "https://cat-fact.herokuapp.com/facts";

    private RequestQueue requestQueue; // object to add the JSON request to queue
    JsonArrayRequest jsonArrayRequest;
    JsonObjectRequest jsonObjectRequest;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        initiateJsonObjectResponse(); //take fact and display on app creation

        textView = findViewById(R.id.textView);
    }

    public void onButtonClick(View v) {
        initiateJsonObjectResponse();
    }

    void initiateJsonObjectResponse() {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                objectRequestURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    textView.setText(response.getString("text"));
                } catch (JSONException e) {
                    Log.d("JSON", "onResponse: response could not be taken");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error: ", "onErrorResponse: " + error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest); //getting response from 1 JSON object
    }

    //not useful here, but can stay for an example in case of upgrade later
    void initiateJsonArrayResponse() {
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                arrayRequestURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error: ", "onErrorResponse: ");
            }
        });
        requestQueue.add(jsonArrayRequest); // getting response from all JSON array
    }
}