package com.myapp.eos_todo.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.eos_todo.R;
import com.myapp.eos_todo.data.database.MyDatabase;
import com.myapp.eos_todo.data.entity.TodoItem;
import com.myapp.eos_todo.update.UpdateTodoActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainTodoAdapter extends RecyclerView.Adapter<MainTodoViewHolder> {

    private ArrayList<TodoItem> itemlist = new ArrayList<>();

    public void submitAll(List<TodoItem> list){
        itemlist.clear();
        final boolean b = itemlist.addAll(list);
        Collections.sort(itemlist);
        notifyDataSetChanged();
    }

    public void addItem(TodoItem item){
        itemlist.add(item);
        notifyDataSetChanged();
    }

    public void removeAllItem(){
        itemlist.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MainTodoViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final MainTodoViewHolder viewHolder =
                new MainTodoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TodoItem temp = itemlist.get(viewHolder.getAdapterPosition());
                temp.setChecked(!temp.getChecked());
                MyDatabase myDatabase = MyDatabase.getInstance(parent.getContext());
                myDatabase.todoDao().UpadteTodo(temp);
                notifyItemChanged(viewHolder.getAdapterPosition());
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final TodoItem temp = itemlist.get(viewHolder.getAdapterPosition());
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(temp.getTitle());
                final String[] items = {"수정", "삭제", "취소"};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position){
                        switch (items[position]){
                            case "수정":
                                Intent intent = new Intent(parent.getContext(), UpdateTodoActivity.class);
                                intent.putExtra("todo_id", temp.getId());
                                parent.getContext().startActivity(intent);
                                break;
                            case "삭제":
                                itemlist.remove(temp);
                                MyDatabase myDatabase = MyDatabase.getInstance(parent.getContext());
                                myDatabase.todoDao().deleteTodo(temp);
                                notifyItemRemoved(viewHolder.getAdapterPosition());
                            case "취소":
                                break;
                        }
                    }
                });
                builder.show();
                return false;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainTodoViewHolder holder, int position) {
        holder.onBind(itemlist.get(position));
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
}
