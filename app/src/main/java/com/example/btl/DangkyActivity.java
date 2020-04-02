package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DangkyActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText username_dk, pass_dk, email_dk;
    Button btn_regist;
    String URL = "https://appdoan.000webhostapp.com/dangkytk.php";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        toolbar = findViewById(R.id.toolbardk);
        username_dk = findViewById(R.id.username_dk);
        pass_dk = findViewById(R.id.pass_dk);
        email_dk = findViewById(R.id.email_dk);
        btn_regist = findViewById(R.id.button_dk);
        ActionToolbar();

        requestQueue = Volley.newRequestQueue(this);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String a = response.trim();
                        if(a.equalsIgnoreCase("Đăng kí thành công, giờ bạn có thể đăng nhập")){
                            Toast.makeText(DangkyActivity.this, response, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangkyActivity.this,DangnhapActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(DangkyActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DangkyActivity.this, error+"", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        String u_name = username_dk.getText().toString();
                        String email = email_dk.getText().toString();
                        String pass = pass_dk.getText().toString();
                        params.put("username",u_name);
                        params.put("email",email);
                        params.put("pass",pass);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_media_rew);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}