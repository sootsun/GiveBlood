package oss.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import oss.data.BoardItem;
import oss.data.REF;

/**
 * @todo 게시판 삭제
 * */
public class ElementActivity extends AppCompatActivity {

    BoardItem boardItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_NeedBlood);
        getSupportActionBar().setTitle("게시물");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element);

        boardItem = getIntent().getParcelableExtra(REF.LIST.name());

        TextView name = findViewById(R.id.element_name);
        TextView writer = findViewById(R.id.element_writer);
        TextView detail = findViewById(R.id.element_detail);

        name.setText(boardItem.boardName);
        writer.setText(boardItem.userName);
        detail.setText(boardItem.boardInfo);

        findViewById(R.id.button).setOnClickListener(v -> {
            finish();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_element, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.element_del:
                FirebaseDatabase.getInstance().getReference()
                        .child(REF.LIST.name())
                        .child(boardItem.pos).removeValue();
                Toast.makeText(this, "삭제됨", Toast.LENGTH_SHORT).show();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}