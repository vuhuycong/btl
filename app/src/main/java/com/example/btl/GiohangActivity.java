package com.example.btl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.btl.adapter.GiohangAdapter;
import com.example.btl.model.Giohang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class GiohangActivity extends AppCompatActivity {
    ListView lvgiohang;
    TextView tvthongbao;
    TextView tvTongtien;
    Button btthanhtoan, bttieptucmua;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        anhxa();
        ActionToolbar();
        checkdata();
        Tinhtien();
        XoaSp();
        ActionButton();
    }

    private void ActionButton() {
        bttieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GiohangActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.giohangArrayList.size() > 0){
                    Intent intent = new Intent(getApplicationContext(),ThongtinKHActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(GiohangActivity.this,"Giỏ Hàng Của Bạn Chưa Có Sản Phẩm",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void XoaSp() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GiohangActivity.this);
                builder.setTitle("Xác Nhận Xóa Sản Phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.giohangArrayList.size() > 0) {
                            MainActivity.giohangArrayList.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            Tinhtien();
                            if(MainActivity.giohangArrayList.size() <=0 ){
                                tvthongbao.setVisibility(View.VISIBLE);
                            }else {
                                tvthongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                Tinhtien();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        Tinhtien();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void Tinhtien() {
        long tongtien = 0;
        for(int i = 0; i<MainActivity.giohangArrayList.size();i++){
            tongtien += MainActivity.giohangArrayList.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTongtien.setText(decimalFormat.format(tongtien)+ " Đ");
    }

    private void checkdata() {
        if(MainActivity.giohangArrayList.size()<=0){
            giohangAdapter.notifyDataSetChanged();
            tvthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }else {
            giohangAdapter.notifyDataSetChanged();
            tvthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_media_rew);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhxa() {
        lvgiohang = findViewById(R.id.lvgiohang);
        tvthongbao = findViewById(R.id.tvthongbao);
        tvTongtien = findViewById(R.id.tvtongtien);
        btthanhtoan = findViewById(R.id.btthanhtoan);
        bttieptucmua = findViewById(R.id.bttieptucmua);
        toolbargiohang = findViewById(R.id.tbgiohang);
        giohangAdapter = new GiohangAdapter(GiohangActivity.this,MainActivity.giohangArrayList);
        lvgiohang.setAdapter(giohangAdapter);
    }

}
