package oss.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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
 * @see oss.main.ActivityLauncher
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
            ActivityLauncher kakaoLauncher = new ActivityLauncher(new NullCommand());
            Intent intent = new Intent(getApplicationContext(), KakaoLoginActivity.class);
            kakaoLauncher.launch(intent);
        });

        emailLogin.setOnClickListener(v -> {
            ActivityLauncher emailLauncher = new ActivityLauncher(new NullCommand());
            Intent intent = new Intent(getApplicationContext(), EmailLoginActivity.class);
            emailLauncher.launch(intent);
        });

        passLogin.setOnClickListener(v -> {
            ActivityLauncher passLauncher = new ActivityLauncher(new NullCommand());
            Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
            intent.putExtra("ID", PASSED);
            passLauncher.launch(intent);
        });
    }
}