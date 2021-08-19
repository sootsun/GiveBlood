package oss.main;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import oss.data.BoardItem;
import oss.data.REF;
import oss.fragment.HomeFragment;

/**
 * @todo 게시판 삭제
 * */
public class ElementActivity extends AppCompatActivity {

    BoardItem boardItem;
    FirebaseUser user;
    private ActivityResultLauncher<Intent> launcher;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_NeedBlood);
        getSupportActionBar().setTitle("게시물");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element);

        user = FirebaseAuth.getInstance().getCurrentUser();
        boardItem = getIntent().getParcelableExtra(REF.LIST.name());
        myRef = FirebaseDatabase.getInstance().getReference(REF.LIST.name());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        BoardItem boardItem = data.getParcelableExtra(REF.LIST.toString());
                        removeData(boardItem.pos);
                        boardItem.pos = myRef.push().getKey();
                        myRef.child(boardItem.pos).setValue(boardItem);
                        HomeFragment.getData();
                    }
                    Toast.makeText(this, "수정됨", Toast.LENGTH_SHORT).show();
                    finish();
                });

        TextView name = findViewById(R.id.element_name);
        TextView writer = findViewById(R.id.element_writer);
        TextView detail = findViewById(R.id.element_detail);

        name.setText(boardItem.boardName);
        writer.setText(boardItem.userName);
        detail.setText(boardItem.boardInfo);

        findViewById(R.id.button).setOnClickListener(v -> finish());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_element, menu);
        MenuItem del = menu.findItem(R.id.element_del);
        MenuItem rew = menu.findItem(R.id.element_rewrite);
        if(boardItem.userMail.equals("익명") ||
                !boardItem.userMail.equals(user.getEmail())) {
            del.setVisible(false);
            rew.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.element_del:
                removeData(boardItem.pos);
                Toast.makeText(this, "삭제됨", Toast.LENGTH_SHORT).show();
                HomeFragment.getData();
                    finish();
                    break;
            case R.id.element_rewrite:
                Intent intent = new Intent(this,WriteActivity.class);
                intent.putExtra(REF.LIST.name(), boardItem);
                launcher.launch(intent);
                }
        return super.onOptionsItemSelected(item);
        }

        private void removeData(String pos) {
            FirebaseDatabase.getInstance().getReference()
                    .child(REF.LIST.name())
                    .child(pos).removeValue();
        }
}