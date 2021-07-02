package com.example.syzer.recycler_view_items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.syzer.R;

import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

    List<WordInList> data;

    public listAdapter(List<WordInList> data){
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_in_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WordInList wordInList = data.get(position);
        holder.word.setText(wordInList.getWord());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView word;

        public ViewHolder(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word_in_list);
        }
    }
}
