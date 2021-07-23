package oss.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Intent intent = getIntent();
        String name = intent.getStringExtra("ID");
        Toast.makeText(this, name+"님 환영합니다", Toast.LENGTH_LONG).show();
    }

    public void fragmentOnClick(View v) {
        return;
    }
}