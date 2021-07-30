package com.myapp.eos_todo.update;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.myapp.eos_todo.R;
import com.myapp.eos_todo.add.AddTodoActivity;
import com.myapp.eos_todo.data.database.MyDatabase;
import com.myapp.eos_todo.data.entity.TodoItem;

import java.util.Calendar;

public class UpdateTodoActivity extends AppCompatActivity {

    private TextInputLayout til_title, til_sDate, til_dDate, til_memo;
    private ImageButton ibtn_sCalendar, ibtn_dCalendar;

    private final int start_date=0;
    private final int due_date=1;

    private TodoItem selectItem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_todo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void showCalender(final int mode){
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int d = cal.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(UpdateTodoActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String date = "" + year + "/" + (month+1) + "/" + dayOfMonth;
                if(mode == start_date){
                    til_sDate.getEditText().setText(date);
                }
                else if(mode == due_date){
                    til_dDate.getEditText().setText(date);
                }
                else{/*TODO: Error*/}
            }
        }, y, m, d).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todo);

        MyDatabase myDatabase = MyDatabase.getInstance(UpdateTodoActivity.this);
        try {
            selectItem = myDatabase.todoDao().getTodo(getIntent().getIntExtra("todo_id", -1));
        }catch (Exception e){
            Log.d("no id", "no id");
            finish();
        }

        til_title = findViewById(R.id.up_til_todo);
        til_sDate = findViewById(R.id.up_til_start_date);
        til_dDate = findViewById(R.id.up_til_due_date);
        til_memo = findViewById(R.id.up_til_memo);

        ibtn_sCalendar = findViewById(R.id.up_ibtn_start_date);
        ibtn_dCalendar = findViewById(R.id.up_ibtn_due_date);

        til_title.getEditText().setText(selectItem.getTitle());
        til_sDate.getEditText().setText(selectItem.getStart_date());
        til_dDate.getEditText().setText(selectItem.getDue());
        til_memo.getEditText().setText(selectItem.getMemo());

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Update Todo");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ibtn_sCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalender(start_date);
            }
        });

        ibtn_dCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalender(due_date);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            case R.id.save_todo:

                String title = til_title.getEditText().getText().toString();
                String sDate = til_sDate.getEditText().getText().toString();
                String dDate = til_dDate.getEditText().getText().toString();
                String memo = til_memo.getEditText().getText().toString();

                if(title.equals("")){
                    til_title.setError("필수요소 입니다.");
                }
                else til_title.setError(null);

                if(sDate.equals("")){
                    til_sDate.setError("필수요소 입니다.");
                }
                else til_sDate.setError(null);

                if(dDate.equals("")){
                    til_dDate.setError("필수요소 입니다.");
                }
                else til_dDate.setError(null);

                if(!title.equals("") && !sDate.equals("") && !dDate.equals("")){
                    if(sDate.compareTo(dDate) > 0){
                        til_sDate.setError("끝나는 날짜가 더 빨라요");
                        til_dDate.setError("끝나는 날짜가 더 빨라요");
                    }
                    else {
                        selectItem.setTitle(title);
                        selectItem.setStart_date(sDate);
                        selectItem.setDue(dDate);
                        selectItem.setMemo(memo);

                        MyDatabase myDatabase = MyDatabase.getInstance(UpdateTodoActivity.this);
                        myDatabase.todoDao().UpadteTodo(selectItem);

                        Toast.makeText(UpdateTodoActivity.this, "저장 성공", Toast.LENGTH_SHORT).show();

                        finish();
                        break;
                    }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
