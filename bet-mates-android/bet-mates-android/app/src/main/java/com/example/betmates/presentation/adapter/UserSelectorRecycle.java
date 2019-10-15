package com.example.betmates.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.betmates.R;
import com.example.betmates.business.AccessBets;
import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.User;

import java.util.ArrayList;

//--------------------------------
// Adapter Feed
//
// PURPOSE: This is an adapter class which handles the Current Bet Recycler View
//--------------------------------
public class UserSelectorRecycle extends RecyclerView.Adapter<UserSelectorRecycle.CurrentBetsViewHolder> {
    private AccessBets betAccessor;
    private ArrayList<User> userList;
    private ArrayList<User> selectedList;
    private Bet currentBet;

    public UserSelectorRecycle(ArrayList<User> data){
        this.userList = data;
        betAccessor = new AccessBets();
        currentBet = betAccessor.getCurrentBet();
        if(currentBet.getJoiners()==null)
        {
            this.selectedList = new ArrayList<User>();
        }
        else
        {
            this.selectedList = currentBet.getJoiners();
        }
    }

    @NonNull
    @Override
    public CurrentBetsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item_friends_selector_layout,viewGroup,false);
        return new CurrentBetsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentBetsViewHolder currentBetsViewHolder, int position) {
        // Gets the bet information.
        final User currentUser = userList.get(position);



        // Display all of the information about a bet.
        currentBetsViewHolder.photo.setImageResource(R.drawable.sample_profile_pic);
        currentBetsViewHolder.name.setText(currentUser.getName());
        boolean contains = false;
        for(int i=0;i<selectedList.size();i++)
        {
           if(selectedList.get(i).getName().equals(currentUser.getName()))
           {
               contains = true;
           }
        }
        if(contains)
        {
            currentBetsViewHolder.checkBox.setChecked(true);
        }
        else
        {
            currentBetsViewHolder.checkBox.setChecked(false);
        }
        currentBetsViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                int index = -1;
                for(int i=0;i<selectedList.size();i++)
                {
                    if(selectedList.get(i).getName().equals(currentUser.getName()))
                    {
                        index = i;
                    }
                }
                if(isChecked)
                {
                    selectedList.add(currentUser);
                }
                else if(index>=0)
                {
                    selectedList.remove(index);
                }
            }
        });
    }

    public ArrayList<User> getSelectedList(){return selectedList;}

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class CurrentBetsViewHolder extends RecyclerView.ViewHolder{
        ImageView photo;
        TextView name;
        CheckBox checkBox;

        public CurrentBetsViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.user_selector_photo);
            name = itemView.findViewById(R.id.user_selector_name);
            checkBox = itemView.findViewById(R.id.user_selector_checkBox);
        }
    }
}
