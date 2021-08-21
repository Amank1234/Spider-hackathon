package com.example.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    Context context;
    ArrayList<ModelAudio> userList;
    int x;

    public Adapter(ArrayList<ModelAudio> userList, Context context) {
        this.userList = userList;
        this.context = context;

    }
    @NonNull
    @Override
    public Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewHolder holder, int position) {


//        if (x == 2) {
//
//            Intent i = new Intent(context, MainActivity2.class);
//            int temp =position;
//            i.putExtra("title", userList.get(temp).getaudioTitle());
//            i.putExtra("artist", userList.get(temp).getaudioArtist());
//            i.putExtra("duration", userList.get(temp).getaudioDuration());
//            i.putExtra("uri", userList.get(temp).getaudioUri().toString());
//            i.putExtra("position", temp);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
//        } else if (x == -2) {
//            Intent i = new Intent(context, MainActivity2.class);
//            i.putExtra("title", userList.get(position - 1).getaudioTitle());
//            i.putExtra("artist", userList.get(position - 1).getaudioArtist());
//            i.putExtra("duration", userList.get(position - 1).getaudioDuration());
//            i.putExtra("uri", userList.get(position - 1).getaudioUri().toString());
//            i.putExtra("position", position - 1);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
//        }
//        else {
String duration = userList.get(position).getaudioDuration();
        int f = Integer.parseInt(duration);
        int s = f/1000;
        int h =s/3600;
        int lefthr = s%3600;
        int min =lefthr/60;
        int sec = lefthr%60;
        String time = h+":"+min+":"+sec;

        holder.textView.setText(userList.get(position).getaudioTitle());
        holder.textView2.setText(userList.get(position).getaudioArtist());
        holder.textView3.setText(time);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity2.class);

                i.putParcelableArrayListExtra("musiclist",userList);
//                Bundle args = new Bundle();
////                args.putSerializable("musiclist",(Serializable)userList);
//                args.putParcelableArrayList("musiclist", (ArrayList<? extends Parcelable>) userList);
//                i.putExtra("Bundle",args);
                i.putExtra("title", userList.get(position).getaudioTitle());
                i.putExtra("artist", userList.get(position).getaudioArtist());
                i.putExtra("duration", userList.get(position).getaudioDuration());
                i.putExtra("uri", userList.get(position).getaudioUri().toString());
                i.putExtra("position", position);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class viewHolder extends ViewHolder {
        private final ImageView imageView1;
        private TextView textView;
        private TextView textView2;
        private TextView textView3;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.imageView1);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);

        }
    }
}
