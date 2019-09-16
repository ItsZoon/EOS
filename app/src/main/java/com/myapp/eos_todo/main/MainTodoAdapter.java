package com.myapp.eos_todo.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.eos_todo.R;
import com.myapp.eos_todo.data.entity.TodoItem;

import java.util.ArrayList;

public class MainTodoAdapter extends RecyclerView.Adapter<MainTodoViewHolder> {

    private ArrayList<TodoItem> itemlist = new ArrayList<>();

    @NonNull
    @Override
    public MainTodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainTodoViewHolder viewHolder =
                new MainTodoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainTodoViewHolder holder, int position) {
        holder.onBind(itemlist.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
