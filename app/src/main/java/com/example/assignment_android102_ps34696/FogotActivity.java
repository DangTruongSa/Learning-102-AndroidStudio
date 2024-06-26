package com.example.assignment_android102_ps34696;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignment_android102_ps34696.dao.nguoidungDAO;
import com.example.assignment_android102_ps34696.util.SendMail;

public class FogotActivity extends AppCompatActivity {
    private nguoidungDAO nguoidungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogot);

        Button btns=findViewById(R.id.sbtn1);
        EditText sedt=findViewById(R.id.sedt2);

        nguoidungDAO = new nguoidungDAO(this);

        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=sedt.getText().toString();

                String matkhau=nguoidungDAO.checkuser(user);
                if (matkhau.length()>0){
                    SendMail sendMail = new SendMail();
                    sendMail.Send(FogotActivity.this,user,"Lấy lại mật khẩu","Mật khẩu của bạn là: "+ matkhau);
                }
            }
        });


    }
}