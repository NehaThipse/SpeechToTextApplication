package com.example.speechtotextapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView voiceInput, speakBtn;
    public static final int REQ_CODE_SR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voiceInput = findViewById(R.id.tv);
        speakBtn = findViewById(R.id.btnSpeak);

        speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // for calling inbuilt speech recognizer
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                // To invoke language support and language form
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                // Give local support to speech recognizer
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                // its my recognizer
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Bol Halke Halke");

                startActivityForResult(intent, REQ_CODE_SR);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SR:
                if(resultCode == RESULT_OK && null !=data)
                {
                    ArrayList<String> stringArrayList= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    voiceInput.setText(stringArrayList.get(0));
                }

                break;


        }
    }
}
