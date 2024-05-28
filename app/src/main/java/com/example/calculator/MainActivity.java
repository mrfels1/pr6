package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Fragment1.OnResClickListener, Fragment2.OnRes2ClickListener {
    ActivityMainBinding binding;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment1, fragment1);
        ft.add(R.id.fragment2, fragment2);
        ft.commit();
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = fragment1.func();
                fragment2.displayResult(result);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment2.hasResult()) {
                    fragment1.resetInput();
                    fragment2.resetInput();
                }
            }
        });
    }
    @Override
    public void onResClick(String result) {
        fragment2.displayResult(result);
    }
    @Override
    public void onRes2Click() {
        fragment1.resetInput();
        fragment2.resetInput();
    }
}
