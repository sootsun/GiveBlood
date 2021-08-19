package oss.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import oss.data.BoardItem;
import oss.data.REF;
import oss.data.UserData;

/**글쓰기 화면
 *
 * */
public class WriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        /*작성 항목*/
        EditText title = findViewById(R.id.write_title_text);
        EditText content = findViewById(R.id.write_content_text);

        /*수정 시 원래 값*/
        BoardItem item = getIntent().getParcelableExtra(REF.LIST.name());
        title.setText(item.title);
        content.setText(item.content);

        /*완료*/
        findViewById(R.id.write_confirm_button).setOnClickListener(v -> {
            Intent intent = new Intent();
            BoardItem boardItem = new BoardItem(title.getText().toString(), content.getText().toString(), new UserData(item.writer, item.email));
            boardItem.key = item.key;
            intent.putExtra(REF.LIST.name(), boardItem);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}