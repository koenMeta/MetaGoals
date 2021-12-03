package com.training.MetaGoals;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private  ArrayList<com.training.MetaGoals.Challenge> challenges = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext, ArrayList<com.training.MetaGoals.Challenge> challenges) {
        this.mContext = mContext;
        this.challenges = challenges;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position)  {
        Log.d(TAG, "onCreateViewHolder: called");

        com.training.MetaGoals.Challenge challenge = challenges.get(position);

        Glide.with(mContext).asBitmap().load(challenge.getChallengeUrl()).into(holder.circleImageView);
        holder.textView.setText(challenges.get(position).getChallengeTitle());

        holder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on an image: " + challenge.getChallengeTitle());
                Toast.makeText(mContext, challenge.getChallengeTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, com.training.MetaGoals.ChallengeActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", challenge.getChallengeTitle());
                bundle.putString("description", challenge.getChallengeDescription());
                bundle.putString("imgUrl", challenge.getChallengeUrl());

                intent.putExtras(bundle);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.imageChallenge);
            textView = itemView.findViewById(R.id.textChallenge);
        }

    }
}
