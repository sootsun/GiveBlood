package oss.fragment;

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

import java.util.ArrayList;

import oss.data.BoardItem;
import oss.data.ItemRecyclerAdapter;
import oss.main.R;

/**
 * @// TODO: 2021-07-27 글쓰기 버튼 구현
 */
public class HomeFragment extends Fragment {

    public RecyclerView recyclerView;
    public ItemRecyclerAdapter itemRecyclerAdapter;
    private FloatingActionButton addButton;
    private ArrayList<BoardItem> boardItemArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.home_recycler);
        itemRecyclerAdapter = new ItemRecyclerAdapter();
        addButton = rootView.findViewById(R.id.home_add_button);

        itemRecyclerAdapter.setItemList(boardItemArrayList);
        recyclerView.setAdapter(itemRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        /*게시판 글쓰기 버튼*/
        addButton.setOnClickListener(v -> {
            boardItemArrayList.add(new BoardItem("Me", "What it is"));
            itemRecyclerAdapter.setItemList(boardItemArrayList);
        });


        return rootView;
    }
}