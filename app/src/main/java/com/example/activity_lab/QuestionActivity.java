package com.example.activity_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private TextView question;
    private Button submit;
    private EditText answer;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Bundle extras = this.getIntent().getExtras();

        this.question = (TextView)this.findViewById(R.id.textQuestion);
        this.question.setText(extras.getString("QUESTION_TEXT"));

        this.answer = (EditText) this.findViewById(R.id.inputAnswer);

        this.submit = (Button)this.findViewById(R.id.buttonSubmitAnswer);
        this.cancel = (Button)this.findViewById(R.id.buttonCancelAnswer);

        this.submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("ANSWER_TEXT", QuestionActivity.this.answer.getText().toString());
                QuestionActivity.this.setResult(Activity.RESULT_OK, returnIntent);
                QuestionActivity.this.finish();
            }
        });

        this.cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                QuestionActivity.this.setResult(Activity.RESULT_CANCELED, returnIntent);
                QuestionActivity.this.finish();
            }
        });
    }
}