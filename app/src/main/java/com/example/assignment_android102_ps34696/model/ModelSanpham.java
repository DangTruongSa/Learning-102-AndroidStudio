package com.example.assignment_android102_ps34696.model;

public class ModelSanpham {
    //coment in model
    public int masp,soluong,gia;
    public String tensp;

    public ModelSanpham(int masp,String tensp,int gia,int soluong){
        this.masp=masp;
        this.tensp=tensp;
        this.gia=gia;
        this.soluong=soluong;

    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public ModelSanpham(String tensp, int gia, int soluong){
        this.tensp=tensp;
        this.gia=gia;
        this.soluong=soluong;

    }

}
