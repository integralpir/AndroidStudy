package com.example.syzer.recycler_view_items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.syzer.R;
import com.example.syzer.data.Number;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    List<Number> data;

    public ListAdapter(List<Number> data){
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_in_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Number wordInList = data.get(position);
        holder.number.setText(wordInList.number);
        holder.fact.setText(wordInList.fact);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateList(List<Number> list){
        data = list;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        TextView fact;

        public ViewHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number_in_list);
            fact = itemView.findViewById(R.id.text_in_list);
        }
    }
}
