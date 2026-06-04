package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private List<Friend> friendList;

    public FriendAdapter(List<Friend> friendList) {
        this.friendList = friendList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        Button addRemoveBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.friendName);
            addRemoveBtn = itemView.findViewById(R.id.addRemoveBtn);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Friend friend = friendList.get(position);

        holder.name.setText(friend.getName());

        holder.addRemoveBtn.setText(friend.isSelected() ? "Remove" : "Add");

        holder.addRemoveBtn.setOnClickListener(v -> {
            friend.setSelected(!friend.isSelected());
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }
}