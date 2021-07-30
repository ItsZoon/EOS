package com.myapp.eos_todo.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Todo")
public class TodoItem implements Comparable<TodoItem> {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "start")
    private String start_date;

    @ColumnInfo(name = "due")
    private String due;

    @ColumnInfo(name = "memo")
    private String memo;

    @ColumnInfo(name = "checked")
    private Boolean checked;

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Ignore
    public TodoItem(String title){
        this.title = title;
        due = null;
        checked = false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public int compareTo(TodoItem o){
        return this.due.compareTo(o.due);
    }
    public TodoItem(String title, String start_date, String due, String memo) {
        this.title = title;
        this.start_date = start_date;
        this.due = due;
        this.memo = memo;
        checked = false;
    }
}
