package com.example.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.btl.MainActivity;
import com.example.btl.R;
import com.example.btl.model.Giohang;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> giohangArrayList;

    public GiohangAdapter(Context context, ArrayList<Giohang> giohangArrayList) {
        this.context = context;
        this.giohangArrayList = giohangArrayList;
    }

    @Override
    public int getCount() {
        return giohangArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return giohangArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView tvtenGiohang,tvgiaGiohang;
        public ImageView anhGiohang;
        public Button bttru,btcong,btgiatri;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null ){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sp_giohang,null);
            viewHolder.tvtenGiohang = view.findViewById(R.id.tvtengiohang);
            viewHolder.tvgiaGiohang = view.findViewById(R.id.tvgiagiohang);
            viewHolder.anhGiohang = view.findViewById(R.id.imggiohang);
//            viewHolder.btcong = view.findViewById(R.id.button_cong);
            viewHolder.btgiatri = view.findViewById(R.id.button_giatri);
//            viewHolder.bttru = view.findViewById(R.id.button_tru);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Giohang giohang = (Giohang) getItem(position);
        viewHolder.tvtenGiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvgiaGiohang.setText(decimalFormat.format(giohang.getGiasp()) + " Đ");
        Glide.with(this.context).load(giohang.getAnhsp()).into(viewHolder.anhGiohang);
        viewHolder.btgiatri.setText(giohang.getSoluongsp() + "");
//        int sl = Integer.parseInt(viewHolder.btgiatri.getText().toString());
//        if(sl >= 10){
//            viewHolder.btcong.setVisibility(View.INVISIBLE);
//            viewHolder.bttru.setVisibility(View.VISIBLE);
//        }else if(sl <= 1){
//            viewHolder.bttru.setVisibility(View.INVISIBLE);
//        }else if(sl >= 1){
//            viewHolder.btcong.setVisibility(View.VISIBLE);
//            viewHolder.bttru.setVisibility(View.VISIBLE);
//        }
//        viewHolder.btcong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int slmoinhat = Integer.parseInt(viewHolder.btgiatri.getText().toString()) + 1;
//                int slht = MainActivity.giohangArrayList.get(position).getSoluongsp();
//                long giaht = MainActivity.giohangArrayList.get(position).getGiasp();
//                MainActivity.giohangArrayList.get(position).setGiasp(slmoinhat);
//                long giamoi = (giaht * slmoinhat) / slht;
//                MainActivity.giohangArrayList.get(position).setGiasp(giamoi);
//                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                viewHolder.tvgiaGiohang.setText(decimalFormat.format(giamoi) + " Đ");
//            }
//        });
        return view;
    }
}
