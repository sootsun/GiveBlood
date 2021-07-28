package oss.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.animation.AccelerateInterpolator;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


/**
 * @author PMS
 * @modified Init -
 * @updated 21.07.22
 *
 * 액티비티 런처
 * 액티비티 간 전환, 복귀 시 할 행동 Command로 넘겨줌
 *
 * */
public class ActivityLauncher extends AppCompatActivity {

    //민석이의 코드
    private Command command;
    private ActivityResultLauncher launcher;

    /**
     * 생성자
     *
     * @param command
     * 복귀시 할 행동
     * */
    public ActivityLauncher(Command command) {
        this.command = command;
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK) {command.execute();}
                });
    }

    /**
     * launch
     *
     * @param intent
     * 넘겨줄 정보 intent에 추가 후 실행
     * */
    public void launch(Intent intent) {
        launcher.launch(intent);
    }

    // GetContent creates an ActivityResultLauncher<String> to allow you to pass
// in the mime type you'd like to allow the user to select
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    // Handle the returned Uri
                }
            });
}
