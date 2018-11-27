package com.example.bfrol.it_samsung;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends Activity {
    Button second_button;
    EditText textEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        second_button = findViewById(R.id.secondButton);
        textEdit = findViewById(R.id.textEdit);
        second_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = textEdit.getText().toString();
                Intent i = new Intent();
                i.putExtra("return_text_tag",newText);
                setResult(6,i);
                finish();
            }
        });
    }
}
