package com.example.dj.flyingbirds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstLook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_look);

    Thread t = new Thread(){
        @Override
        public void run() {
            try {
                sleep(5000);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                Intent intent = new Intent(FirstLook.this,MainActivity.class);
                startActivity(intent);
            }
        }
    };
        t.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
