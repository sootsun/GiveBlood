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

public class FireBaseSingleTon {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private int count = 0;

    private static FireBaseSingleTon myFireBase = new FireBaseSingleTon();
    private static ArrayList<BoardItem> itemArrayList = new ArrayList<>();
    public static ItemRecyclerAdapter itemRecyclerAdapter = new ItemRecyclerAdapter();

    private FireBaseSingleTon() {
        this.database = FirebaseDatabase.getInstance();
        this.myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                itemArrayList.clear();
                Log.d("MY", Integer.toString(count++));
                for(DataSnapshot dataSnapshot : snapshot.child(Refs.USER.toString()).getChildren()) {
                    BoardItem item = dataSnapshot.getValue(BoardItem.class);
                    itemArrayList.add(item);
                }
                itemRecyclerAdapter.setItemList(itemArrayList);
            }
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

    public void setMyRef(String message) {
        this.myRef = database.getReference(message);
    }
}
