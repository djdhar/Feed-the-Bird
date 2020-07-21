package com.example.dj.flyingbirds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private String finalscore = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        button = findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.score);
        finalscore = getIntent().getStringExtra("points");

       // textView.setText(finalscore);
        textView.setText("Your Points : "+ finalscore);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
