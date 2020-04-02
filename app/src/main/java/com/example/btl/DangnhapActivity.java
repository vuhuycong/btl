package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DangnhapActivity extends AppCompatActivity {
    EditText username_dn, pass_dn;
    Button btn_login;
    Toolbar toolbar;
    String URL_DN = "https://appdoan.000webhostapp.com/dangnhaptk.php";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        toolbar = findViewById(R.id.toolbardn);
        username_dn = findViewById(R.id.username_dn);
        pass_dn = findViewById(R.id.pass_dn);
        btn_login = findViewById(R.id.button_dn);
        requestQueue = Volley.newRequestQueue(this);

        ActionToolbar();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DN, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String a = response.trim();
                        if(a.equalsIgnoreCase("Đăng nhập thành công")){
                            Toast.makeText(DangnhapActivity.this, response, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplication(),AccActivity.class);
                            intent.putExtra("Username",username_dn.getText().toString());
                            startActivity(intent);
                        }else {
                            Toast.makeText(DangnhapActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DangnhapActivity.this, error+"", Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String> getParams() throws AuthFailureError{
                        Map<String,String> params = new HashMap<String,String>();
                        String username = username_dn.getText().toString();
                        String pass = pass_dn.getText().toString();
                        params.put("username",username);
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