package com.example.assignment_android102_ps34696.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_android102_ps34696.R;
import com.example.assignment_android102_ps34696.adapter.AdapterSanpham;
import com.example.assignment_android102_ps34696.dao.sanphamDAO;
import com.example.assignment_android102_ps34696.model.ModelSanpham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragmentview extends Fragment {
    private sanphamDAO sanphamDAO;
    private AdapterSanpham AdapterSanpham;

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentview,container,false);

        recyclerView = view.findViewById(R.id.rcyv);
        floatingActionButton = view.findViewById(R.id.floget);

        sanphamDAO = new sanphamDAO(getContext());
        loaddata();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogadd();
            }
        });


        return view;
    }

    public void loaddata(){
        ArrayList<ModelSanpham> ls = sanphamDAO.getDS();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        AdapterSanpham adt = new AdapterSanpham( sanphamDAO,ls, getContext());
        recyclerView.setAdapter(adt);
    }

    private void showdialogadd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add,null);
        builder.setView(view);


        AlertDialog alertDialog =builder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        //responsive

        EditText edtten=view.findViewById(R.id.edttensp);
        EditText edtgia=view.findViewById(R.id.edtgiasp);
        EditText edtso=view.findViewById(R.id.edtsoluongsp);
        Button btnadd=view.findViewById(R.id.btnthemsp);
        Button btncen=view.findViewById(R.id.btnhuy);
        
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensp=edtten.getText().toString();
                String gia=edtgia.getText().toString();
                String soluong=edtso.getText().toString();
                
                if (tensp.length() == 0 || gia.length() == 0 || soluong.length() == 0){
                    Toast.makeText(getContext(), "Hãy nhập đầy đủ thông tin!!", Toast.LENGTH_SHORT).show();
                }else {
                    ModelSanpham modelSanpham = new ModelSanpham(tensp,Integer.parseInt(gia),Integer.parseInt(soluong));
                    boolean check = sanphamDAO.addsp(modelSanpham);
                    if (check){
                        Toast.makeText(getContext(), "Đã thêm sp mới!!", Toast.LENGTH_SHORT).show();
                        loaddata();
                        alertDialog.dismiss();

                    }else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btncen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        
    }
}
