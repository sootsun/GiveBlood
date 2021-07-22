package oss.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 *
 * @author PMS
 *
 * @modified Init -
 * @updated 21.07.22
 *
 *  보드 액티비티
 * 게시판 구현 화면
 *
 * @see oss.util.ActivityLauncher
 * */
public class BoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
    }
}