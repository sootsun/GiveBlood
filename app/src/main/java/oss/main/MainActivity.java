package oss.main;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

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
    private static final int RC_SIGN_IN = 900;

    // 구글api클라이언트
    private GoogleSignInClient googleSignInClient;

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    // 구글  로그인 버튼
    private SignInButton buttonGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_NeedBlood);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonGoogle = findViewById(R.id.main_google_button);

        // Google 로그인을 앱에 통합
        // GoogleSignInOptions 개체를 구성할 때 requestIdToken을 호출
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        Button kakaoLogin = findViewById(R.id.main_kakao_button);
        Button passLogin = findViewById(R.id.main_pass_button);

        kakaoLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, KakaoLoginActivity.class));
        });


        passLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, BoardActivity.class));
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 구글로그인 버튼 응답
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // 구글 로그인 성공
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(MainActivity.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                        } else {
                            // 로그인 실패
                            Toast.makeText(MainActivity.this, R.string.failed_login, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

}