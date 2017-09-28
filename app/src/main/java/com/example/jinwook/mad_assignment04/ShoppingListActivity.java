package com.example.jinwook.mad_assignment04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jinwook on 2017-09-28.
 */

public class ShoppingListActivity extends AppCompatActivity {

    private TextView tvTotalPrice, tvTitle,tvAdress, tvDial, tvReq, tvTP, tvHowtopay;
    private EditText etAddress, etDial;
    private Button btOrder, btCancel;

    private float totalPrice =0;

    private Spinner spinner;
    private SpinnerAdapter sAdapter;
    private String language;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppinglist);

        Intent intent = getIntent();
        totalPrice = intent.getFloatExtra("totalPrice", 0);
        language = intent.getStringExtra("language");
        etAddress = (EditText)findViewById(R.id.et_address);
        etDial = (EditText)findViewById(R.id.et_dial);
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvAdress = (TextView)findViewById(R.id.tv_address);
        tvDial = (TextView)findViewById(R.id.tv_dial);
        tvReq = (TextView)findViewById(R.id.tv_req);
        tvTP = (TextView)findViewById(R.id.tv_tp);
        tvHowtopay = (TextView)findViewById(R.id.tv_howtopay);
        tvTotalPrice = (TextView)findViewById(R.id.tv_total_price);
        spinner = (Spinner) findViewById(R.id.order_type);
        btOrder = (Button) findViewById(R.id.bt_order);

        // 한국 일때와 미국일 때 나눴다.
        if(language.equals("kor")){
            tvTotalPrice.setText(Integer.toString((int)totalPrice));
            tvTotalPrice.append("원");
            sAdapter = ArrayAdapter.createFromResource(this, R.array.order, android.R.layout.simple_spinner_dropdown_item);
        }else if(language.equals("eng")){
            tvTotalPrice.setText("$"+Float.toString(totalPrice));
            tvTitle.setText("Ordering");
            tvAdress.setText("Address in Detail");
            tvDial.setText("Phone Number");
            tvReq.setText("Have any Requirement?");
            tvTP.setText("Total Price");
            tvHowtopay.setText("How to Pay?");
            btOrder.setText("Ordering Complete!");
            etAddress.setHint("Write your detail Address");
            sAdapter = ArrayAdapter.createFromResource(this, R.array.order_eng, android.R.layout.simple_spinner_dropdown_item);
        }
        spinner.setAdapter(sAdapter);

        btOrder.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                // 주소와 휴대폰 번호는 필수 입력
                Log.d("MAD_devEqual",String.valueOf(etAddress.getText().toString().equals("")));

                if ( etAddress.getText().toString().equals("")){
                    Toast.makeText(ShoppingListActivity.this, "배달할 주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else if(etDial.getText().toString().equals("")){
                    Toast.makeText(ShoppingListActivity.this, "핸드폰 번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(),OrderingActivity.class);
                    startActivity(intent);
                    finish();


                }
            }
        });

        btCancel = (Button)findViewById(R.id.bt_cancel);
        btCancel.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
