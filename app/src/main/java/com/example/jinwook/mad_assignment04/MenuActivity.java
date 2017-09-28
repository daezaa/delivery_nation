package com.example.jinwook.mad_assignment04;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jinwook on 2017-09-27.
 */


public class MenuActivity extends AppCompatActivity {

    private LinearLayout[] LLMenu = new LinearLayout[12];
    private TextView[] tvMenu = new TextView[12];
    private TextView[] tvPrice = new TextView[12];
    private TextView[] tvNumber = new TextView[12];
    private Button[] btPlus = new Button[12];
    private Button[] btMinus = new Button[12];
    private Button btBack, btCalling, btShopping;
    private ImageButton btHeart, btHeart_red;
    private TextView tvTitle;

    private String[] myPrice = new String[12];
    private int loopNum;
    private String myCountry, myNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String myName = intent.getStringExtra("name");
        myCountry = intent.getStringExtra("country");
        myNumber = intent.getStringExtra("number");
        String[] myMenu = intent.getStringArrayExtra("menu");
        myPrice = intent.getStringArrayExtra("price");
        loopNum = myMenu.length;

        // 식당 제목
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText(myName);
        btCalling = (Button)findViewById(R.id.bt_calling);
        btShopping = (Button)findViewById(R.id.bt_shopping);
        // 언어
        if (myCountry.equals("eng")){
            btCalling.setText("Order By Calling");
            btShopping.setText("Shopping Cart");
        }

        // 반복문으로 선언
        int resID;
        String pkgName = getPackageName();
        for (int i=0;i<loopNum;i++){

            // 0번째 배열은 Skip
            String num = String.valueOf(i+1);

            // 메뉴이름과 가격 수정
            resID = getResources().getIdentifier("tv_menu"+num, "id", pkgName);
            tvMenu[i+1] = (TextView)findViewById(resID);
            tvMenu[i+1].setText(myMenu[i]);
            resID = getResources().getIdentifier("tv_price"+num, "id", pkgName);
            tvPrice[i+1] = (TextView)findViewById(resID);
            tvPrice[i+1].setText(myPrice[i]);

            // 가격
            if (myCountry.equals("kor")){
                myPrice[i] = myPrice[i].replace("원","");
            }else if(myCountry.equals("eng")){
                myPrice[i] = myPrice[i].replace("$","");
            }

            // 아이템 개수만큼 보이게
            resID = getResources().getIdentifier("LL_menu"+num,"id",pkgName);
            LLMenu[i+1] = (LinearLayout)findViewById(resID);
            LLMenu[i+1].setVisibility(View.VISIBLE);

            // 수량
            resID = getResources().getIdentifier("num"+num, "id", pkgName);
            tvNumber[i+1] = (TextView)findViewById(resID);


            // Plus Button
            resID = getResources().getIdentifier("plus"+num, "id", pkgName);
            btPlus[i+1] = (Button)findViewById(resID);
            btPlus[i+1].setOnClickListener(listener);

            // Minus Button
            resID = getResources().getIdentifier("minus"+num, "id", pkgName);
            btMinus[i+1] = (Button)findViewById(resID);
            btMinus[i+1].setOnClickListener(listener);
        }

        // heart Button
        btHeart = (ImageButton)findViewById(R.id.bt_heart);
        btHeart.setOnClickListener(heartListener);
        btHeart_red = (ImageButton)findViewById(R.id.bt_heart_red);
        btHeart_red.setOnClickListener(heartListener);

        // back button
        btBack = (Button)findViewById(R.id.bt_back);
        btBack.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // call button
        btCalling.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {


                // 사용자의 OS버전이 마시멜로우 이상인지 체크한다.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int permissionResult = checkSelfPermission(Manifest.permission.CALL_PHONE);
                    // permission이 거부되어있는지 확인한다.
                    if (permissionResult == PackageManager.PERMISSION_DENIED) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MenuActivity.this);
                            dialog.setTitle("권한이 필요합니다")
                                    .setMessage("이 기능을 사용하기 위해서는 단말기의 \"전화걸기\"권한이 필요합니다. 계속하시겠습니까?")
                                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // 버전 체크
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                                            }
                                        }
                                    })
                                    .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(MenuActivity.this, "기능을 취소했습니다.", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .create()
                                    .show();
                        }
                        // 최초 권한 요청
                        else {
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                        }
                    }
                    //권한이 있을 때
                    else {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+myNumber));
                        startActivity(intent);
                    }
                }
                //마시멜로우 이하 버전
                else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+myNumber));
                    startActivity(intent);
                }
            }
        });
        // shopping cart button
        btShopping.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                float totalPrice=0;

                for(int i=0;i<loopNum;i++){
                    totalPrice += ( Float.parseFloat(myPrice[i]) ) * ( Float.parseFloat(tvNumber[i+1].getText().toString()) );
                }
                if (totalPrice ==0){
                    if(myCountry.equals("kor")){
                        Toast.makeText(getApplicationContext(), "적어도 한 메뉴는 선택해주셔야 합니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Please, Choose at least one menu.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Intent intent = new Intent(getApplicationContext(),ShoppingListActivity.class);
                    intent.putExtra("totalPrice", totalPrice);
                    intent.putExtra("language", myCountry);
                    startActivity(intent);
                }
            }
        });
    }
    // plus minus button
    Button.OnClickListener listener = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            String pkgName = getPackageName();
            int rid1, rid2;
            for (int i=0; i<loopNum;i++){
                String num = String.valueOf(i+1);
                rid1 = getResources().getIdentifier("plus"+num, "id", pkgName);
                rid2 = getResources().getIdentifier("minus"+num, "id", pkgName);

                // Plus button Clicked
                if (v.getId()==rid1){
                    tvNumber[i+1].setText(String.valueOf(Integer.parseInt(tvNumber[i+1].getText().toString())+1));
                }
                // Minus button Clicked
                else if(v.getId()==rid2){
                    if((Integer.parseInt(tvNumber[i+1].getText().toString()))>0) {
                        tvNumber[i+1].setText(String.valueOf(Integer.parseInt(tvNumber[i + 1].getText().toString()) - 1));
                    }
                }

            }
        }
    };

    ImageButton.OnClickListener heartListener = new ImageButton.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_heart:
                    btHeart_red.setVisibility(View.VISIBLE);
                    btHeart.setVisibility(View.GONE);

                    break;

                case R.id.bt_heart_red:
                    btHeart.setVisibility(View.VISIBLE);
                    btHeart_red.setVisibility(View.GONE);

                    break;

            }
        }
    };
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if (requestCode ==1000){
            // 허용할 시
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+myNumber));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                    startActivity(intent);
                }
            }else{
                Toast.makeText(MenuActivity.this,"권한요청을 거부했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
