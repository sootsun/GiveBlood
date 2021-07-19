package org.techtown.giveblood;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK) {
                    Toast.makeText(this, "이제 로그인 해", Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_GiveBlood);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button introduce = findViewById(R.id.myIntro);
        Button loginKaKao = findViewById(R.id.loginKakao);
        Button loginEmail = findViewById(R.id.loginEmail);
        Button addMember = findViewById(R.id.addMember);

        introduce.setOnClickListener(v -> {
            Toast.makeText(this, "Start Intro", Toast.LENGTH_LONG).show();
        });

        loginKaKao.setOnClickListener(v -> {
            Toast.makeText(this, "KaKao Login", Toast.LENGTH_LONG).show();
        });

        loginEmail.setOnClickListener(v -> {
            Intent intent = new Intent(this, EmailLoginActivity.class);
            launcher.launch(intent);
        });

        addMember.setOnClickListener(v -> {
            //Intent intent = new Intent(this, SignUpActivity.class);
            //launcher.launch(intent);
        });
    }
}