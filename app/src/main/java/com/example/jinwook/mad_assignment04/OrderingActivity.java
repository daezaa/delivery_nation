package com.example.jinwook.mad_assignment04;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Jinwook on 2017-09-28.
 */

public class OrderingActivity extends AppCompatActivity {


    private ImageView ivOrdering;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering);

        ivOrdering = (ImageView)findViewById(R.id.iv_ordering);
        final AnimationDrawable drawable = (AnimationDrawable) ivOrdering.getBackground();
        drawable.start();


        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);

                Toast.makeText(OrderingActivity.this, "주문이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0,5000);
    }
}
