package com.example.todoandroid;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.todoandroid.entity.Todo;

public class FormTodo extends AppCompatActivity {

    private EditText titleText;
    private EditText descriptionText;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_todo);

        titleText = findViewById(R.id.titleText);

        descriptionText = findViewById(R.id.decriptionText);

        buttonSend = findViewById(R.id.btnSend);


        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                Todo todo = new Todo(titleText.getText().toString(),descriptionText.getText().toString(),false);
                returnIntent.putExtra("todo",todo);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}