package com.example.assignment_android102_ps34696.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_android102_ps34696.MainActivity;
import com.example.assignment_android102_ps34696.R;
import com.example.assignment_android102_ps34696.dao.sanphamDAO;
import com.example.assignment_android102_ps34696.model.ModelSanpham;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class AdapterSanpham extends RecyclerView.Adapter<MynewSanpham> {
    private sanphamDAO sanphamDAO;
    private ModelSanpham ModelSanpham;
    ArrayList<ModelSanpham> ds;
    Context c;

    public AdapterSanpham(com.example.assignment_android102_ps34696.dao.sanphamDAO sanphamDAO, ArrayList<com.example.assignment_android102_ps34696.model.ModelSanpham> ds, Context c) {
        this.sanphamDAO = sanphamDAO;
        this.ds = ds;
        this.c = c;
    }


    @NonNull
    @Override
    public MynewSanpham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = ((MainActivity)c).getLayoutInflater();
        View v = inf.inflate(R.layout.oneitemsanpham,parent,false);
        return new MynewSanpham(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MynewSanpham holder, int position) {
        ModelSanpham modelSanpham=ds.get(position);
        holder.tv.setText(modelSanpham.tensp);

        NumberFormat formatter = new DecimalFormat("#,###");
        double myNumber = modelSanpham.gia;
        String formattedNumber = formatter.format(myNumber);

        holder.tv4.setText(formattedNumber+" VND");
        holder.tv5.setText("-SL : "+modelSanpham.soluong);

        //sua sp

        holder.tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate(ds.get(holder.getAdapterPosition()));
            }
        });

        // xoasp

        holder.tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogxoasp(ds.get(holder.getAdapterPosition()).getTensp(),ds.get(holder.getAdapterPosition()).getMasp());

            }
        });
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    private void showDialogUpdate(ModelSanpham modelSanpham){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater inflater= ((MainActivity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        EditText edttensp=view.findViewById(R.id.edttensp);
        EditText edtgiasp=view.findViewById(R.id.edtgiasp);
        EditText edtsoluopngsp=view.findViewById(R.id.edtsoluongsp);
        Button btnsuasp=view.findViewById(R.id.btnsuasp);
        Button btncensp=view.findViewById(R.id.btnhuy1);

        // dua dulieu

        edttensp.setText(modelSanpham.getTensp());
        edtgiasp.setText(String.valueOf(modelSanpham.getGia()));
        edtsoluopngsp.setText(String.valueOf(modelSanpham.getSoluong()));

        btnsuasp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masp=modelSanpham.getMasp();
                String ten=edttensp.getText().toString();
                String gia=edtgiasp.getText().toString();
                String soluong=edtsoluopngsp.getText().toString();

                if (ten.length() == 0 || gia.length() == 0 || soluong.length() == 0){
                    Toast.makeText(c, "Hãy nhập đầy đủ thông tin!!", Toast.LENGTH_SHORT).show();
                }else {
                    ModelSanpham modelSanpham = new ModelSanpham(masp,ten,Integer.parseInt(gia),Integer.parseInt(soluong));
                    boolean check = sanphamDAO.chinhsua(modelSanpham);
                    if (check){
                        Toast.makeText(c, "Chỉnh sửa thành công!!", Toast.LENGTH_SHORT).show();

                        ds.clear();
                        ds = sanphamDAO.getDS();
                        notifyDataSetChanged();
                        alertDialog.dismiss();

                    }else {
                        Toast.makeText(c, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });




        btncensp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void showdialogxoasp(String tensp,int masp){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Thông báo");
        builder.setMessage("bạn có muốn xóa sản phẩm\""+tensp+"\" không?");

        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check= sanphamDAO.Xoasp(masp);
                if (check){
                    Toast.makeText(c, "Xóa thành công!!", Toast.LENGTH_SHORT).show();

                    ds.clear();
                    ds = sanphamDAO.getDS();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(c, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy",null);
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }

}
