package oss.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import oss.data.BoardItem;
import oss.data.REF;

/**글쓰기 화면
 * @todo 스피너 구현
 * */
public class WriteActivity extends AppCompatActivity {

    EditText patientName;
    EditText patientNum;
    EditText hospital;
    EditText room;
    Spinner bloodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        /*작성 항목*/
        patientName = findViewById(R.id.elem_patient_name);
        patientNum = findViewById(R.id.elem_patient_num);
        hospital = findViewById(R.id.elem_hospital);
        room = findViewById(R.id.elem_room);
        bloodType = findViewById(R.id.spinner);


        /*수정 시 원래 값*/
        BoardItem item = getIntent().getParcelableExtra(REF.LIST.name());
        getData(item);

        /*완료*/
        findViewById(R.id.elem_confirm_button).setOnClickListener(v -> {
            Intent intent = new Intent();
            setData(item);
            intent.putExtra(REF.LIST.name(), item);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void getData(BoardItem item) {
        patientName.setText(item.patient);
        patientNum.setText(item.patientNum);
        hospital.setText(item.hospital);
        room.setText(item.room);
    }
    private void setData(BoardItem item) {
        item.patient = patientName.getText().toString();
        item.patientNum = patientNum.getText().toString();
        item.hospital = hospital.getText().toString();
        item.room = room.getText().toString();
    }
}