package oss.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

/**
 * @// TODO: 2021-07-27 글쓰기 버튼 구현
 */
@IgnoreExtraProperties
public class HomeFragment extends Fragment {

    public RecyclerView recyclerView;
    public ItemRecyclerAdapter itemRecyclerAdapter;
    private FloatingActionButton addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.home_recycler);
        itemRecyclerAdapter = new ItemRecyclerAdapter();
        addButton = rootView.findViewById(R.id.home_add_button);

        itemRecyclerAdapter.setItemList(FireBaseSingleTon.getItemArrayList());
        recyclerView.setAdapter(itemRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        /*게시판 글쓰기 버튼*/
        addButton.setOnClickListener(v -> {
            DatabaseReference myRef = FireBaseSingleTon.getMyFireBase().getMyRef();

            Intent intent = getActivity().getIntent();
            String name = intent.getStringExtra(Refs.ID.toString());
            String info = intent.getStringExtra(Refs.INFO.toString());

            BoardItem boardItem = new BoardItem(name, info);
            myRef.child(Refs.USER.toString()).push().setValue(boardItem);
            itemRecyclerAdapter.notifyDataSetChanged();
        });

        return rootView;
    }
}