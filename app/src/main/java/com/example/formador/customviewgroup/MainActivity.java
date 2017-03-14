package com.example.formador.customviewgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View view) {

        CounterView counterView = (CounterView) findViewById(R.id.counter1);
        Toast.makeText(this, String.valueOf(counterView.getCounter()), Toast.LENGTH_SHORT).show();
    }
}
