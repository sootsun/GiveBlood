package org.techtown.giveblood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.regex.Pattern;

public class EmailLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        EditText userName = findViewById(R.id.username);
        EditText passWord = findViewById(R.id.password);

        Button login = findViewById(R.id.login);

        login.setOnClickListener(v -> {
            if(!isAppropriateUserName(userName.getText())) {
                Toast.makeText(this, "이메일 형식" ,Toast.LENGTH_LONG).show();
                return;
            }
            if(!isInMember(userName.getText(), passWord.getText())) {
                Toast.makeText(this, "Not in member", Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show();
        });
    }

    private boolean isAppropriateUserName(Editable userName){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(userName.toString()).matches();
    }

    private boolean isInMember(Editable userName, Editable passWord) {
        return true;
    }
}