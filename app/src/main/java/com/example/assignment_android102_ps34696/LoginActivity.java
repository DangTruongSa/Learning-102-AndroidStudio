package com.example.assignment_android102_ps34696;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_android102_ps34696.dao.nguoidungDAO;
import com.example.assignment_android102_ps34696.util.SendMail;

public class LoginActivity extends AppCompatActivity {
    private nguoidungDAO nguoidungDAO;
    EditText edtuser,edtpass;
    TextView signup,fogot;
    Button btnlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup=findViewById(R.id.txtsignup);
        fogot=findViewById(R.id.txtfogot);

        edtuser=findViewById(R.id.edt1);
        edtpass=findViewById(R.id.edt2);

        btnlogin=findViewById(R.id.btn1);

        nguoidungDAO=new nguoidungDAO(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=edtuser.getText().toString();
                String pass=edtpass.getText().toString();

                boolean check = nguoidungDAO.checkLogin(user, pass);
                if(check){
                    Toast.makeText(LoginActivity.this, "Dang Nhap Thanh Cong", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Dang Nhap That bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fogot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogfoget();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });



    }

    private void showDialogfoget(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_fogot,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtEmail=view.findViewById(R.id.sedt2);
        Button btnsend=view.findViewById(R.id.sbtn1);
        Button btncencel=view.findViewById(R.id.sbtn2);

        btncencel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setCancelable(true);
            }
        });

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=edtEmail.getText().toString();

                String matkhau=nguoidungDAO.checkuser(user);
                if (matkhau.length()>0){
                    SendMail sendMail = new SendMail();
                    sendMail.Send(LoginActivity.this,user,"Lấy lại mật khẩu","Mật khẩu của bạn là: "+ matkhau);
                }
            }
        });

    }
}