package com.example.betmates.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.betmates.R;
import com.example.betmates.objects.content.Bet;

import java.util.ArrayList;

//--------------------------------
// Adapter Feed
//
// PURPOSE: This is an adapter class which handles the Current Bet Recycler View
//--------------------------------
public class RecyclerViewBetAdapter extends RecyclerView.Adapter<RecyclerViewBetAdapter.CurrentBetsViewHolder> {
    private ArrayList<Bet> currentBetList;

    public RecyclerViewBetAdapter(ArrayList<Bet> data){
        this.currentBetList = data;
    }

    @NonNull
    @Override
    public CurrentBetsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item_layout,viewGroup,false);
        return new CurrentBetsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentBetsViewHolder currentBetsViewHolder, int position) {
        // Gets the bet information.
        Bet bet = currentBetList.get(position);
        String setValue = "Amount: " + bet.getAmount();
        String setCreator = "The bet was Created by: " + bet.getCreator().getName();

        // Display all of the information about a bet.
        currentBetsViewHolder.betDescription.setText(bet.getBetDescription());
        currentBetsViewHolder.Amount.setText(setValue);
        currentBetsViewHolder.creator.setText(setCreator);

    }

    @Override
    public int getItemCount() {
        return currentBetList.size();
    }

    public class CurrentBetsViewHolder extends RecyclerView.ViewHolder{
        TextView betDescription;
        TextView Amount;
        TextView creator;

        public CurrentBetsViewHolder(@NonNull View itemView) {
            super(itemView);
            betDescription = itemView.findViewById(R.id.BetDescription);
            Amount = itemView.findViewById(R.id.Amount);
            creator = itemView.findViewById(R.id.creator);
        }
    }
}
