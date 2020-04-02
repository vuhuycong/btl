package com.example.btl.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class DoAn implements Serializable {
    private String TenDoAn,LinkAnh,ThongtinDoan;
    private int id, GiaDoAn;


    public DoAn(){

    }
    public DoAn(JSONObject o) throws JSONException {
        id = o.getInt("Id");
        TenDoAn = o.getString("tenDoan");
        LinkAnh = o.getString("image");
        GiaDoAn = o.getInt("giaDoan");
        ThongtinDoan = o.getString("ttDoan");
    }

    public DoAn(String tenDoAn, String linkAnh, String thongtinDoan, int id, int giaDoAn) {
        TenDoAn = tenDoAn;
        LinkAnh = linkAnh;
        ThongtinDoan = thongtinDoan;
        this.id = id;
        GiaDoAn = giaDoAn;
    }

    public String getTenDoAn() {
        return TenDoAn;
    }

    public void setTenDoAn(String tenDoAn) {
        TenDoAn = tenDoAn;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }

    public String getThongtinDoan() {
        return ThongtinDoan;
    }

    public void setThongtinDoan(String thongtinDoan) {
        ThongtinDoan = thongtinDoan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGiaDoAn() {
        return GiaDoAn;
    }

    public void setGiaDoAn(int giaDoAn) {
        GiaDoAn = giaDoAn;
    }
}

