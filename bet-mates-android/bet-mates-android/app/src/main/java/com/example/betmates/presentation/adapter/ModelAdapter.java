package com.example.betmates.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betmates.R;
import com.example.betmates.objects.content.Model;
import com.example.betmates.presentation.AcceptRejectBet;

import java.util.ArrayList;


//--------------------------------
// Adapter Feed
//
// PURPOSE:This is an adapter class which handles the feed Recycler View
//--------------------------------
public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.MyViewHolder> {

    Context context;
    ArrayList<Model> modelArrayList;

    public ModelAdapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_item_layout,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int i) {
        final Model model = modelArrayList.get(i);

        holder.tv_name.setText(model.getName());
        holder.tv_status.setText(model.getStatus());

        holder.tv_time.setText(model.getTime());
        holder.placeBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Placing bet ", Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent(context, AcceptRejectBet.class);
                intent.putExtra("Bet Description", modelArrayList.get(holder.getAdapterPosition()).getBet().getBetDescription());
                intent.putExtra("Bet Name", modelArrayList.get(holder.getAdapterPosition()).getBet().getBetName());
                intent.putExtra("Bet Amount", Double.toString(modelArrayList.get(holder.getAdapterPosition()).getBet().getAmount()));
                intent.putExtra("Bet Creator", modelArrayList.get(holder.getAdapterPosition()).getBet().getCreator().getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_time, tv_likes,tv_status, placeBet;
        ImageView imgview_postPic, imgview_proPic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgview_proPic = itemView.findViewById(R.id.viewProfPic);

            tv_name = itemView.findViewById(R.id.name);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_time = itemView.findViewById(R.id.Time);
            placeBet = itemView.findViewById(R.id.PlaceBet);
        }
    }

}
