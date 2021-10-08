package com.example.activity_lab;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText question;
    private Button submit;
    private TextView lastAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.question = (EditText)this.findViewById(R.id.inputQuestion);
        this.lastAnswer = (TextView) this.findViewById(R.id.textAnswer);

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            String answerString = data.getStringExtra("ANSWER_TEXT");
                            MainActivity.this.lastAnswer.setText(answerString);
                        } else if(result.getResultCode() == Activity.RESULT_CANCELED) {
                            MainActivity.this.lastAnswer.setText("операция отменена");
                        }
                    }
                });

        this.submit = (Button)this.findViewById(R.id.buttonSubmitQuestion);
        this.submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                String questionText = MainActivity.this.question.getText().toString();
                intent.putExtra("QUESTION_TEXT", questionText);
                someActivityResultLauncher.launch(intent);
            }
        });
    }
}