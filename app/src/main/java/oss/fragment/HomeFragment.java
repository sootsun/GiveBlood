package oss.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

import oss.data.BoardItem;
import oss.data.FireBaseSingleTon;
import oss.data.ItemRecyclerAdapter;
import oss.data.Refs;
import oss.main.R;
import oss.main.WriteActivity;

/**
 * @// TODO: 2021-07-27 글쓰기 버튼 구현
 */
@IgnoreExtraProperties
public class HomeFragment extends Fragment {

    public RecyclerView recyclerView;
    public ItemRecyclerAdapter itemRecyclerAdapter;
    private FloatingActionButton addButton;

    private DatabaseReference myRef;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myRef = FireBaseSingleTon.getMyFireBase().getMyRef();
        itemRecyclerAdapter = new ItemRecyclerAdapter();
        itemRecyclerAdapter.setItemList(FireBaseSingleTon.getItemArrayList());
        itemRecyclerAdapter.notifyDataSetChanged();

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Log.d("LU", "start");
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Log.d("LU", "do");
                        Intent data = result.getData();

                        //Log.d("LU", name);

                        BoardItem boardItem = data.getParcelableExtra(Refs.DATA.toString());
                        myRef.child(Refs.DATA.toString()).push().setValue(boardItem);
                        itemRecyclerAdapter.notifyDataSetChanged();
                    }
                    Log.d("LU", "finish");
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.home_recycler);
        addButton = rootView.findViewById(R.id.home_add_button);

        recyclerView.setAdapter(itemRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        /*게시판 글쓰기 버튼*/
        addButton.setOnClickListener(v -> {

            /*USER 데이터*/
            //Intent auth = getActivity().getIntent();
            //String name = auth.getStringExtra(Refs.ID.toString());
            //String info = auth.getStringExtra(Refs.INFO.toString());

            Intent intent = new Intent(getContext(), WriteActivity.class);
            launcher.launch(intent);
        });

        return rootView;
    }
}