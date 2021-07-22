package oss.main;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import oss.util.Command;
import oss.util.NullCommand;

/**
 *
 * @author PMS
 *
 * @modified Init -
 * @updated 21.07.22
 *
 * 메인 액티비티
 * 로그인 버튼 구현
 *
 * @see Command
 * */
public class MainActivity extends AppCompatActivity {
    public static final String PASSED = "PASSED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_NeedBlood);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button kakaoLogin = findViewById(R.id.main_kakao_button);
        Button emailLogin = findViewById(R.id.main_email_button);
        Button passLogin = findViewById(R.id.main_pass_button);

        kakaoLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, KakaoLoginActivity.class));
        });

        emailLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, EmailLoginActivity.class));
        });

        passLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, BoardActivity.class));
        });
    }

}