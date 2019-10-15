package com.example.betmates.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.betmates.R;
import com.example.betmates.objects.content.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserListViewHolder>  {
    private ArrayList<User> usersList;

    public UserAdapter(ArrayList<User> users){
        this.usersList = users;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item_user_layout,viewGroup,false);
        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder userListViewHolder, int position) {
        User user = usersList.get(position);
        String userName = user.getName();
        String balance = "Balance: " + user.getBalance();

        userListViewHolder.name.setText(userName);
        userListViewHolder.balance.setText(balance);
    }


    @Override
    public int getItemCount() {
        if(usersList!=null)
        {
            return usersList.size();
        }
        else
        {
            return 0;
        }
    }

    public class UserListViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView balance;

        public UserListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.UserName);
            balance = itemView.findViewById(R.id.UserBalance);
        }
    }
}
