package com.example.jinwook.mad_assignment04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout menu1_1, menu1_2, menu2_1, menu2_2;
    private ImageButton btLangKor, btLangEng;
    private LinearLayout layoutKorean, layoutEnglish;
    private String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestLocale loc = new TestLocale(this);
        country = loc.printLocale();

        menu1_1 = (RelativeLayout)findViewById(R.id.RL_menu1_1);
        menu1_1.setOnClickListener(listener);
        menu1_2 = (RelativeLayout)findViewById(R.id.RL_menu1_2);
        menu1_2.setOnClickListener(listener);
        menu2_1 = (RelativeLayout)findViewById(R.id.RL_menu2_1);
        menu2_1.setOnClickListener(listener);
        menu2_2 = (RelativeLayout)findViewById(R.id.RL_menu2_2);
        menu2_2.setOnClickListener(listener);

        layoutKorean = (LinearLayout)findViewById(R.id.LL_kor);
        layoutEnglish = (LinearLayout)findViewById(R.id.LL_eng);

        btLangKor = (ImageButton)findViewById(R.id.bt_lang_kor);
        btLangEng = (ImageButton)findViewById(R.id.bt_lang_eng);


        // locale 설정값
        if (country.equals("KR")){

        }else if (country.equals("US")){

            btLangKor.setVisibility(View.GONE);
            btLangEng.setVisibility(View.VISIBLE);

            layoutKorean.setVisibility(View.GONE);
            layoutEnglish.setVisibility(View.VISIBLE);

        }else{
            Toast.makeText(this, "This Application is only supported in Korea, and United States", Toast.LENGTH_SHORT).show();
        }



        btLangKor.setOnClickListener(new ImageButton.OnClickListener(){

            @Override
            public void onClick(View v) {
                btLangKor.setVisibility(View.GONE);
                btLangEng.setVisibility(View.VISIBLE);

                layoutKorean.setVisibility(View.GONE);
                layoutEnglish.setVisibility(View.VISIBLE);


            }
        });
        btLangEng.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                btLangEng.setVisibility(View.GONE);
                btLangKor.setVisibility(View.VISIBLE);

                layoutKorean.setVisibility(View.VISIBLE);
                layoutEnglish.setVisibility(View.GONE);
            }
        });



    }

    RelativeLayout.OnClickListener listener = new RelativeLayout.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.RL_menu1_1:
                    Intent intent1 = new Intent(getApplication(), MenuActivity.class);
                    String myName = "배달돼지";
                    String myCountry = "kor";
                    String myNumber = "+82)2-8888-8888";
                    String[] myMenu = new String[] {"한우카르파쵸", "투쁠안심",  "포항초전병", "떡튀김","한라봉 스무디", "쌕쌕감귤"};
                    String[] myPrice = new String[] {"18000원","20000원", "10000원", "3000원", "2000원", "2000원"};
                    intent1.putExtra("name",myName);
                    intent1.putExtra("country",myCountry);
                    intent1.putExtra("number", myNumber);
                    intent1.putExtra("menu",myMenu);
                    intent1.putExtra("price",myPrice);

                    startActivity(intent1);
                    break;

                case R.id.RL_menu1_2:
                    Intent intent2 = new Intent(getApplication(), MenuActivity.class);
                    String myName2 = "스까덮밥";
                    String myCountry2 = "kor";
                    String myNumber2 = "+82)2-8888-8888";
                    String[] myMenu2 = new String[] {"새우장 덮밥","성게비빔밥","슴슴한 낙지덮밥","또띠아위에 꼬막","맛있는 구절판","수정과","식혜"};
                    String[] myPrice2 = new String[] {"6500원","7000원", "7000원", "10000원", "12000원", "1000원", "1000원"};
                    intent2.putExtra("name",myName2);
                    intent2.putExtra("country",myCountry2);
                    intent2.putExtra("number",myNumber2);
                    intent2.putExtra("menu",myMenu2);
                    intent2.putExtra("price",myPrice2);
                    startActivity(intent2);
                    break;
                case R.id.RL_menu2_1:
                    Intent intent3 = new Intent(getApplication(), MenuActivity.class);
                    String myName3 = "Burger God";
                    String myCountry3 = "eng";
                    String myNumber3 = "+1)661-631-0149";
                    String[] myMenu3 = new String[] {"MegaMac","Golden Egg Cheeze Burger","Grilled Mushroom Burger","Double Quarter Cheese Burger", "Crispy Oriental Chicken Burger","Double Chicken Box", "Mac and Cheese",  "Hash Brown","Coke", "Zero Coke","Sprite"};
                    String[] myPrice3 = new String[] {"$8","$6.5", "$6.5", "$7", "$7", "$12","$3","$3","$2","$2","$2"};
                    intent3.putExtra("name",myName3);
                    intent3.putExtra("country",myCountry3);
                    intent3.putExtra("number", myNumber3);
                    intent3.putExtra("menu",myMenu3);
                    intent3.putExtra("price",myPrice3);
                    startActivity(intent3);
                    break;
                case R.id.RL_menu2_2:
                    Intent intent4 = new Intent(getApplication(), MenuActivity.class);
                    String myName4 = "Coffee Heaven";
                    String myCountry4 = "eng";
                    String myNumber4 = "+1)661-284-5959";
                    String[] myMenu4 = new String[] {"Ice Americano", "Cappuccino",  "Cafe latte", "Vanilla Shake"};
                    String[] myPrice4 = new String[] {"$3","$3.5", "$3.5", "$4"};
                    intent4.putExtra("name",myName4);
                    intent4.putExtra("country",myCountry4);
                    intent4.putExtra("number",myNumber4);
                    intent4.putExtra("menu",myMenu4);
                    intent4.putExtra("price",myPrice4);
                    startActivity(intent4);
                    break;


            }
        }
    };

    public class TestLocale{
        Context mContext;

        public TestLocale (Context context){
            mContext = context;
        }

        public String printLocale(){
            Locale locale = mContext.getResources().getConfiguration().locale;
            String displayCountry = locale.getDisplayCountry();
            String country = locale.getCountry();
            String language = locale.getLanguage();
            Log.d("SSongShrimpTruck", "displayCountry : "  + displayCountry  );
            Log.d("SSongShrimpTruck", "country : "    + country  );
            Log.d("SSongShrimpTruck", "language : "   + language  );

            return country;
        }
    }
}
