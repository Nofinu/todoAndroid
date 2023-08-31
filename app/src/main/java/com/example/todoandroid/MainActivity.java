package com.example.todoandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.todoandroid.entity.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonAdd;

    private List<Todo> todos = new ArrayList<>();

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.btnAddTodo);
        linearLayout = findViewById(R.id.todoList);

//        if(!todos.isEmpty()){
//            todos.forEach(t ->{
//                Button button = new Button(MainActivity.this);
//                button.setText(t.getTitle());
//                linearLayout.addView(button);
//            });
//        }


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, FormTodo.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                Todo todo = (Todo) data.getSerializableExtra("todo");
                todos.add(todo);
                System.out.println(todos);
                refredTodo();
            }
        }
    }


    private void refredTodo (){
        linearLayout.removeAllViews();
        todos.forEach(t->{
            Button button = new Button(MainActivity.this);
            button.setText(t.getTitle());
            button.setId(todos.indexOf(t));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = view.getId();
                    todos.get(id).setCompleted(!t.isCompleted());
                    refredTodo();
                }
            });
            if(t.isCompleted()){
                button.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else{
                button.setBackgroundColor(getResources().getColor(R.color.red));
            }

            linearLayout.addView(button);
        });
    }
}