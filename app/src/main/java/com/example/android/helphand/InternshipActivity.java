package com.example.android.helphand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InternshipActivity extends AppCompatActivity {
    Button btn1, btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship);
        btn1 = (Button) findViewById(R.id.button2) ;
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InternshipActivity.this, SocialActivity.class);
                InternshipActivity.this.startActivity(intent);
            }
        });

        btn2 = (Button) findViewById(R.id.button5);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InternshipActivity.this, GoForInternshipActivity.class);
                InternshipActivity.this.startActivity(intent);
            }
        });
    }
}
