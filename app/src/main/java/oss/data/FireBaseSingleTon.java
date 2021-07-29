package oss.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import oss.util.Command;
import oss.util.NullCommand;

/**
 * 파이어베이스 데이터베이스 싱글톤 클래스
 *
 * 데이터베이스 변경 시 아이템 리스트 변경됨
 * 하지만 게시판 내용물은 안바뀌고
 * @see ItemRecyclerAdapter notifyDataSetChanged() 호출 시 업데이트
 *
 * */
public class FireBaseSingleTon {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    //private int count = 0;

    private static FireBaseSingleTon myFireBase = new FireBaseSingleTon();
    private static ArrayList<BoardItem> itemArrayList = new ArrayList<>();
    public static ItemRecyclerAdapter itemRecyclerAdapter = new ItemRecyclerAdapter();

    private FireBaseSingleTon() {
        this.database = FirebaseDatabase.getInstance();
        this.myRef = database.getReference();

        myRef.child(Refs.USER.toString()).setValue("Init");

        //데이터 베이스 변경 리스너
        myRef.addValueEventListener(new ValueEventListener() {

            //변경 되었을 시 할 행동
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                itemArrayList.clear();
                //Log.d("MY", Integer.toString(count++));

                //리스트 새로 업데이트
                for(DataSnapshot dataSnapshot : snapshot.child(Refs.DATA.toString()).getChildren()) {
                    BoardItem item = dataSnapshot.getValue(BoardItem.class);
                    itemArrayList.add(item);
                }
                itemRecyclerAdapter.setItemList(itemArrayList);
            }

            //취소
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.w("FireBase", "Failed to read value.", error.toException());
            }
        });
    }

    public static FireBaseSingleTon getMyFireBase() {
        return myFireBase;
    }

    public DatabaseReference getMyRef() { return myRef; }

    public static ArrayList<BoardItem> getItemArrayList() { return itemArrayList; }

}
