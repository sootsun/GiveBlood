package oss.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import oss.data.BoardItem;
import oss.data.REF;

/**
 * @todo 게시판 삭제
 * */
public class ElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_NeedBlood);
        getSupportActionBar().setTitle("게시물");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element);

        BoardItem item = getIntent().getParcelableExtra(REF.LIST.name());

        TextView name = findViewById(R.id.element_name);
        TextView writer = findViewById(R.id.element_writer);
        TextView detail = findViewById(R.id.element_detail);

        name.setText(item.boardName);
        writer.setText(item.userName);
        detail.setText(item.boardInfo);

        findViewById(R.id.button).setOnClickListener(v -> {
            finish();
        });

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child(REF.LIST.name())
                .child("boardName");
    }
}