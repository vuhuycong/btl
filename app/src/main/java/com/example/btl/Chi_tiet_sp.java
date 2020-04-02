package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btl.model.DoAn;
import com.example.btl.model.Giohang;
import com.example.btl.GiohangActivity;

import java.text.DecimalFormat;


public class Chi_tiet_sp extends AppCompatActivity {
    Toolbar toolbarChitiet;
    ImageView imgChitiet;
    TextView tvtensp,tvgiasp,tvthongtin;
    Spinner spinner;
    Button buttonDatmua;

    int id;
    String TenChitiet = "";
    String LinkAnhDoan = "";
    int GiaChitiet;


    DoAn doAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sp);
        AnhXa();
        ActionToolbar();
        init();
        setUp();
        actionSpinner();
        themgiohangClick();
    }

    private void themgiohangClick() {
        buttonDatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.giohangArrayList.size() >0){
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean check = false;
                    for (int i=0; i<MainActivity.giohangArrayList.size(); i++){
                        if(MainActivity.giohangArrayList.get(i).getIdsp() == id){
                            MainActivity.giohangArrayList.get(i).setSoluongsp(MainActivity.giohangArrayList.get(i).getSoluongsp() + sl);
                            if(MainActivity.giohangArrayList.get(i).getSoluongsp() >=10){
                                MainActivity.giohangArrayList.get(i).setSoluongsp(10);
                            }
                            MainActivity.giohangArrayList.get(i).setGiasp(GiaChitiet * MainActivity.giohangArrayList.get(i).getSoluongsp());
                            check = true;
                        }
                    }
                    if(check == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong * GiaChitiet;
                        MainActivity.giohangArrayList.add(new Giohang(id,TenChitiet,Giamoi,LinkAnhDoan,soluong));
                    }

                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * GiaChitiet;
                    MainActivity.giohangArrayList.add(new Giohang(id,TenChitiet,Giamoi,LinkAnhDoan,soluong));
                }
                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        Bundle b =getIntent().getBundleExtra("data");
        doAn = (DoAn)b.getSerializable("doan");
    }


    private void ActionToolbar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_media_rew);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarChitiet = findViewById(R.id.toolbarchitietsp);
        imgChitiet = findViewById(R.id.anhchitietsp);
        tvtensp = findViewById(R.id.tvtenchitietsp);
        tvgiasp = findViewById(R.id.tvgiachitietsp);
        tvthongtin = findViewById(R.id.tvthongtinsp);
        spinner = findViewById(R.id.spinnersp);
        buttonDatmua = findViewById(R.id.buttondathang);
    }

    private void setUp(){
        id = doAn.getId();
        TenChitiet = doAn.getTenDoAn();
        LinkAnhDoan = doAn.getLinkAnh();
        GiaChitiet = doAn.getGiaDoAn();
        tvtensp.setText(TenChitiet);
        Glide.with(this).load(LinkAnhDoan).into(imgChitiet);
        tvthongtin.setText(doAn.getThongtinDoan());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvgiasp.setText("Giá : "+ decimalFormat.format(GiaChitiet) + " Đ");
    }

    private void actionSpinner(){
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

}
