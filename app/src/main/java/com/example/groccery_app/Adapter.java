package com.example.groccery_app;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ListItem> lists;
    private LayoutInflater inflater;
    private Activity context;
    int gPosition = -1;

    public Adapter(Activity ctx, List<ListItem> lists) {
        this.lists = lists;
        this.inflater = LayoutInflater.from(ctx);
        this.context = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        gPosition = holder.getPosition();
        String title = lists.get(position).get_name();
        String status = lists.get(position).get_bought();
        holder.nTitle.setText(title);
//        holder.nStatus.setChecked(status == "true" ? true : false);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nTitle;
        CheckBox nStatus;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            nTitle = itemView.findViewById(R.id.List_name);
//            nStatus = itemView.findViewById(R.id.checkBox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(nStatus.isChecked())
                    {

                    }
                }
            });

        }


    }


}
