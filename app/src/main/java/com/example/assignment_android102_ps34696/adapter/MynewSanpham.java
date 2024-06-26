package com.example.assignment_android102_ps34696.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_android102_ps34696.R;

public class MynewSanpham extends RecyclerView.ViewHolder {

    ImageView iv;
    TextView tv,tv4,tv5,tv6,tv7;

    public MynewSanpham(@NonNull View itemView) {
        super(itemView);
        iv = (ImageView) itemView.findViewById(R.id.imageView3);
        tv = (TextView) itemView.findViewById(R.id.textView);
        tv4 = (TextView) itemView.findViewById(R.id.textView4);
        tv5 = (TextView) itemView.findViewById(R.id.textView5);
        tv6 = (TextView) itemView.findViewById(R.id.textView6);
        tv7 = (TextView) itemView.findViewById(R.id.textView7);

    }
}
