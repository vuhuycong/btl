package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class ThongtinKHActivity extends AppCompatActivity {
    EditText tenKh,sdtKh,diachiKh;
    Button bt_trove,bt_dathang;
    RequestQueue requestQueue;
    String URL = "http://appdoan.000webhostapp.com/donhang.php";
    String URLchitietdh = "http://appdoan.000webhostapp.com/chitietdonhang.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_kh);
        tenKh = findViewById(R.id.tenkh);
        sdtKh = findViewById(R.id.sdtkh);
        diachiKh = findViewById(R.id.diachikh);
        bt_dathang = findViewById(R.id.button_xn);
        bt_trove = findViewById(R.id.button_trv);

        requestQueue = Volley.newRequestQueue(this);
        bt_dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
//                        Log.d( "madonhang",madonhang);
//                        if(Integer.parseInt(madonhang) > 0){
//                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//                            StringRequest request = new StringRequest(Request.Method.POST, URLchitietdh, new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//                                    if(response.equals("1")){
//                                        MainActivity.giohangArrayList.clear();
//                                        Toast.makeText(ThongtinKHActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                                        startActivity(intent);
//                                    }else {
//                                        Toast.makeText(ThongtinKHActivity.this, "Giỏ hàng bị lỗi", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            }, new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//
//                                }
//                            }){
//                                @Override
//                                protected Map<String, String> getParams() throws AuthFailureError {
//                                    JSONArray jsonArray = new JSONArray();
//                                    for(int i = 0; i<MainActivity.giohangArrayList.size(); i++){
//                                        JSONObject jsonObject = new JSONObject();
//                                        try {
//                                            jsonObject.put("madonhang", madonhang);
//                                            jsonObject.put("masanpham", MainActivity.giohangArrayList.get(i).getIdsp());
//                                            jsonObject.put("tensanpham", MainActivity.giohangArrayList.get(i).getTensp());
//                                            jsonObject.put("giasanpham", MainActivity.giohangArrayList.get(i).getGiasp());
//                                            jsonObject.put("soluongsanpham", MainActivity.giohangArrayList.get(i).getSoluongsp());
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                        jsonArray.put(jsonObject);
//                                    }
//                                    HashMap<String,String> hashMap = new HashMap<String, String>();
//                                    hashMap.put("json", jsonArray.toString());
//                                    return hashMap;
//                                }
//                            };
//                            queue.add(request);
//                        }
                        String a = response.trim();
                        if(a.equalsIgnoreCase("Lỗi")){
                            Toast.makeText(ThongtinKHActivity.this, response, Toast.LENGTH_SHORT).show();
                        }else if(a.equalsIgnoreCase("Mời bạn nhập đầy đủ thông tin")){
                            Toast.makeText(ThongtinKHActivity.this, response, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ThongtinKHActivity.this, response, Toast.LENGTH_SHORT).show();
                            MainActivity.giohangArrayList.clear();
                            Intent intent1 = new Intent(ThongtinKHActivity.this,MainActivity.class);
                            startActivity(intent1);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThongtinKHActivity.this, error+"", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        String tenkh = tenKh.getText().toString();
                        String sdtkh = sdtKh.getText().toString();
                        String diachikh = diachiKh.getText().toString();
                        params.put("ten_kh",tenkh);
                        params.put("sdtkh",sdtkh);
                        params.put("dia_chi",diachikh);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

}
