package oss.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import oss.util.ActivityLauncher;
import oss.util.NullCommand;


//@@@@@@@@@@@@@@@@@@@@@dhfhfhfhfhㅇㅗ로로로로로로로
public class EmailLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        EditText email = findViewById(R.id.email_id_edit);
        EditText password = findViewById(R.id.email_pw_edit);

        Button login = findViewById(R.id.email_login_button);
        Button findId = findViewById(R.id.email_findid_button);
        Button findPw = findViewById(R.id.email_findpw_button);

        login.setOnClickListener(v -> {
            Editable id = email.getText();
            Editable pw = password.getText();

            //이메일 유효성 검사
            if(!isValidEmail(id)) {
                Toast.makeText(this, "이메일 형식으로", Toast.LENGTH_LONG).show();
                return;
            }

            //인텐트로 넘기기
            Intent intent = new Intent(this, BoardActivity.class);
            intent.putExtra("ID", id);

            //실행
            ActivityLauncher launcher = new ActivityLauncher(new NullCommand());
            launcher.launch(intent);
        });

        findId.setOnClickListener(v -> {
            //아이디 찾기
        });

        findPw.setOnClickListener(v -> {
            //비밀번호 찾기
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}