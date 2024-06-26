package com.example.assignment_android102_ps34696;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.assignment_android102_ps34696.dao.nguoidungDAO;

public class RegisterActivity extends AppCompatActivity {

    private nguoidungDAO nguoidungDAO;
    EditText redt1,redt2,redt3,redt4;

    Button btnre;
    ImageButton imbtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imbtn1=findViewById(R.id.imageButton3);

        redt1=findViewById(R.id.edt3);
        redt2=findViewById(R.id.edt4);
        redt3=findViewById(R.id.edt5);
        redt4=findViewById(R.id.edt6);

        btnre=findViewById(R.id.btn2);

        nguoidungDAO = new nguoidungDAO(this);

        btnre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=redt1.getText().toString().trim();
                String pass=redt2.getText().toString().trim();
                String repass=redt3.getText().toString().trim();
                String name=redt4.getText().toString().trim();

                if(user.length()>0 && pass.length()>0 && repass.length()>0){
                    if (repass.equals(pass)){

                        boolean check = nguoidungDAO.register(user,pass,name);

                        if (check){
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this, "Dang ky khong thanh con", Toast.LENGTH_SHORT).show();
                        }


                    }else {
                        Toast.makeText(RegisterActivity.this, "Nhap mat khau khong thanh cong!!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "Hay nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }


            }
        });

        imbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}