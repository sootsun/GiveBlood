package oss.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import oss.data.BoardItem;
import oss.data.REF;
import oss.data.UserData;

public class WriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        EditText id = findViewById(R.id.editTextTextPersonName);
        EditText info = findViewById(R.id.editTextTextPersonName2);


        findViewById(R.id.button).setOnClickListener(v -> {
            UserData user = getIntent().getParcelableExtra(REF.USER.name());

            Intent intent = new Intent();
            BoardItem boardItem = new BoardItem(id.getText().toString(), info.getText().toString(), user);

            intent.putExtra(REF.DATA.name(), boardItem);

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}