package com.example.btl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.btl.adapter.DoanAdapter;
import com.example.btl.api.ApiLayDoan;
import com.example.btl.interfaces.LayDLDoan;
import com.example.btl.model.DoAn;
import com.example.btl.model.Giohang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayDLDoan {
    GridView gvDSDoan;
    DoanAdapter adapter;
    ArrayList<DoAn> doAnArrayList;
    public static ArrayList<Giohang> giohangArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarmhchinh);
        setSupportActionBar(toolbar);

        init();
        anhXa();
        setUp();
        setC();
        new ApiLayDoan(this).execute();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.dangnhap:
                Intent intent = new Intent(MainActivity.this,DangnhapActivity.class);
                startActivity(intent);
                break;
            case R.id.dangki:
                Intent intent1 = new Intent(MainActivity.this,DangkyActivity.class);
                startActivity(intent1);
                break;
            case R.id.thongtin:
                Intent intent2 = new Intent(MainActivity.this,lienheActivity.class);
                startActivity(intent2);
                break;
            case R.id.giohang:
                Intent intent3 = new Intent(MainActivity.this,GiohangActivity.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(){
        doAnArrayList = new ArrayList<>();

        adapter = new DoanAdapter(this,0,doAnArrayList);
    }
    private void anhXa(){
        gvDSDoan = findViewById(R.id.gvDSDoan);

        if(giohangArrayList != null){

        }else {
            giohangArrayList = new ArrayList<>();
        }
    }
    private void setUp(){
        gvDSDoan.setAdapter(adapter);
    }

    private void setC(){
        gvDSDoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DoAn doAn = doAnArrayList.get(position);
                Bundle b = new Bundle();
                b.putSerializable("doan",doAn);
                Intent intent = new Intent(MainActivity.this,Chi_tiet_sp.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this,"Dang Lay Du Lieu",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            doAnArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i=0;i<arr.length();i++){
                JSONObject o = arr.getJSONObject(i);
                doAnArrayList.add(new DoAn(o));
            }
            adapter = new DoanAdapter(this,0,doAnArrayList);
            gvDSDoan.setAdapter(adapter);
        }catch (JSONException e){

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Loi Ket Noi",Toast.LENGTH_SHORT).show();
    }
}
