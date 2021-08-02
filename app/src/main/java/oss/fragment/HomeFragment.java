package oss.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import oss.data.BoardItem;
import oss.data.BoardItemAdapter;
import oss.data.REF;
import oss.data.UserData;
import oss.main.R;
import oss.main.WriteActivity;

/**
 * @// TODO: 2021-07-27 글쓰기 버튼 구현
 */
@IgnoreExtraProperties
public class HomeFragment extends Fragment {

    public RecyclerView recyclerView;
    private FloatingActionButton addButton;

    private DatabaseReference myRef;
    private ActivityResultLauncher<Intent> launcher;
    public BoardItemAdapter boardItemAdapter;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myRef = FirebaseDatabase.getInstance().getReference();
        boardItemAdapter = new BoardItemAdapter();

        myRef.addValueEventListener(new ValueEventListener() {

            //변경 되었을 시 할 행동
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                ArrayList<BoardItem> itemArrayList = new ArrayList<>();

                //리스트 새로 업데이트
                for(DataSnapshot dataSnapshot : snapshot.child(REF.DATA.name()).getChildren()) {
                    BoardItem item = dataSnapshot.getValue(BoardItem.class);
                    itemArrayList.add(item);
                }
                boardItemAdapter.setItemList(itemArrayList);
            }

            //취소
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.w("FireBase", "Failed to read value.", error.toException());
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        //Log.d("LU", name);

                        BoardItem boardItem = data.getParcelableExtra(REF.DATA.toString());
                        myRef.child(REF.DATA.toString()).push().setValue(boardItem);
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.home_recycler);
        addButton = rootView.findViewById(R.id.home_add_button);

        recyclerView.setAdapter(boardItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        /*게시판 글쓰기 버튼*/
        addButton.setOnClickListener(v -> {

            /*USER 데이터*/
            Intent auth = getActivity().getIntent();
            UserData userData = auth.getParcelableExtra(REF.USER.name());

            Intent intent = new Intent(getContext(), WriteActivity.class);
            intent.putExtra(REF.USER.name(), userData);
            launcher.launch(intent);
        });

        return rootView;
    }
}