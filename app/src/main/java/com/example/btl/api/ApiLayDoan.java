package com.example.btl.api;

import android.os.AsyncTask;

import com.example.btl.interfaces.LayDLDoan;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiLayDoan extends AsyncTask<Void,Void,Void> {
    String data;
    LayDLDoan layDLDoan;

    public ApiLayDoan(LayDLDoan layDLDoan) {
        this.layDLDoan = layDLDoan;
        this.layDLDoan.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://appdoan.000webhostapp.com/datafood.php").build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException e){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(data == null){
            this.layDLDoan.biLoi();
        }else {
            this.layDLDoan.ketThuc(data);
        }
    }
}
