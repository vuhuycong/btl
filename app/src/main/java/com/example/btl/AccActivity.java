package com.example.btl;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);
        Intent intent =getIntent();
        TextView hello = findViewById(R.id.hello);
        String username = intent.getStringExtra("Username");
        hello.setText("Xin Chào "+ username);

        button = findViewById(R.id.button_mh);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}