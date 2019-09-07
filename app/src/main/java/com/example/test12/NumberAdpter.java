package com.example.test12;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NumberAdpter extends RecyclerView.Adapter<NumberAdpter.ViewHolder> {
    private Context mContext;
    private List<Number> mNumberList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView numberImage;
        TextView numberName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            numberImage = itemView.findViewById(R.id.number_image);
            numberName = itemView.findViewById(R.id.number_name);
        }
    }
    public NumberAdpter(List<Number> numberList){
        mNumberList = numberList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.number_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Number number = mNumberList.get(position);
                Intent intent = new Intent(mContext,NumberActivity.class);
                intent.putExtra(NumberActivity.NUMBER_NAME,number.getName());
                intent.putExtra(NumberActivity.NUMBER_IMAGE_ID,number.getImgeId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }
    public void onBindViewHolder(@NonNull NumberAdpter.ViewHolder holder, int position) {
        Number number = mNumberList.get(position);
        holder.numberName.setText(number.getName());
        Glide.with(mContext).load(number.getImgeId()).into(holder.numberImage);
    }

    @Override
    public int getItemCount() {
        return mNumberList.size();
    }
}
