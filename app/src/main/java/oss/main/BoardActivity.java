package oss.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import oss.data.REF;
import oss.data.UserData;
import oss.fragment.ChatFragment;
import oss.fragment.HomeFragment;
import oss.fragment.NearFagment;

/**
 * 게시판 액티비티
 * 
 * @see HomeFragment ,NearFagment,ChatFragment
 * @ TODO: 2021-07-26 설정 화면 구현
 * */
public class BoardActivity extends AppCompatActivity {
    HomeFragment homeFragment;
    NearFagment nearFagment;
    ChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //액션바 보이기
        setTheme(R.style.Theme_NeedBlood);
        getSupportActionBar().setTitle("게시판");
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        //계정정보
        Intent intent = getIntent();
        UserData user = intent.getParcelableExtra(REF.USER.name());
        Toast.makeText(this, user.userName+"님 환영합니다", Toast.LENGTH_LONG).show();


        /*프래그먼트*/
        homeFragment = new HomeFragment();
        //nearFagment = new NearFagment();
        chatFragment = new ChatFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    //Toast.makeText(getApplicationContext(), "HOME", Toast.LENGTH_LONG).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    return true;
                case R.id.nearMap:
                    Toast.makeText(getApplicationContext(), "Near", Toast.LENGTH_LONG).show();
                    //getSupportFragmentManager().beginTransaction().replace(R.id.container, nearFagment).commit();
                    return true;
                case R.id.chatting:
                    //Toast.makeText(getApplicationContext(), "Chat", Toast.LENGTH_LONG).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, chatFragment).commit();
                    return true;
                case R.id.setting:
                    //설정 화면 시작
                    Toast.makeText(getApplicationContext(), "Set", Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        });
    }

}