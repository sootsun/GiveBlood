package oss.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import oss.main.R;

/**
 * 게시판 항목 뷰 어댑터
 *
 * @see BoardItem
 *
 * */
public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder> {

    private ArrayList<BoardItem> itemList;

    @NonNull
    @NotNull
    @Override
    public ItemRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(itemList.get(position));
    }

    public void setItemList(ArrayList<BoardItem> list){
        this.itemList = list;
        //notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView info;
        TextView profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = (TextView) itemView.findViewById(R.id.item_profile);
            info = (TextView) itemView.findViewById(R.id.item_info);
        }

        void onBind(BoardItem item){
            profile.setText(item.name);
            info.setText(item.info);
        }
    }
}
