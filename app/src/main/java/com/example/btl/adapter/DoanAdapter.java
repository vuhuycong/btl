package com.example.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.btl.R;
import com.example.btl.model.DoAn;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DoanAdapter extends ArrayAdapter<DoAn> {
    private Context ct;
    private ArrayList<DoAn> arr;

    public DoanAdapter(@NonNull Context context, int resource, @NonNull List<DoAn> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_doan,null);
        }

        if(arr.size()>0){
            DoAn doAn = this.arr.get(position);
            TextView tenDoan = convertView.findViewById(R.id.tvTenDoan);
            TextView giaDoan = convertView.findViewById(R.id.tvGiaDoan);
            ImageView imgDoan = convertView.findViewById(R.id.ivAnhDoan);

            tenDoan.setText(doAn.getTenDoAn());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            giaDoan.setText(decimalFormat.format(doAn.getGiaDoAn())+" Đ");
            Glide.with(this.ct).load(doAn.getLinkAnh()).into(imgDoan);

        }
        return convertView;
    }
}
