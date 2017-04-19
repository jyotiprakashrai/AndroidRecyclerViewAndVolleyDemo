package com.example.jyotiprakash.volleyexample2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WingnitActorInfo extends AppCompatActivity {
    TextView textView6,textView7,textView8;
    Button button;
    String url="http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";
    MySingleton singleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wingnit_actor_info);
        textView6= (TextView) findViewById(R.id.textview6);
        textView7= (TextView) findViewById(R.id.textview7);
        textView8= (TextView) findViewById(R.id.textview8);
        button= (Button) findViewById(R.id.button1);


        singleton=MySingleton.getInstance(getApplicationContext());


        StringRequest stringWinRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("actors");
                    JSONObject jsonObject1=jsonArray.getJSONObject(0);

                    singleton.setDob(jsonObject1.getString("dob"));
                    singleton.setCountry(jsonObject1.getString("country"));
                    singleton.setHeight(jsonObject1.getString("height"));


                    textView6.setText(singleton.getDob());
                    textView7.setText(singleton.getCountry());
                    textView8.setText(singleton.getHeight());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        singleton.getRequestQueue().add(stringWinRequest);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
