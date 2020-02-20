package com.supremesir.uidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView display;
    Button buttonLeft, buttonRight, buttonConfirm;
    Switch aSwitch;
    ProgressBar progressBar;
    EditText editText;
    RadioGroup radioGroup;
    ImageView imageView;
    SeekBar seekBar;
    CheckBox checkBoxYuwen, checkBoxShuxue, checkBoxYingyu;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.textView);
        buttonLeft = findViewById(R.id.button);
        buttonRight = findViewById(R.id.button2);
        buttonConfirm = findViewById(R.id.button3);
        aSwitch = findViewById(R.id.switch1);
        progressBar = findViewById(R.id.progressBar3);
        editText = findViewById(R.id.editText2);
        radioGroup = findViewById(R.id.radioGroup1);
        imageView = findViewById(R.id.imageView);
        seekBar = findViewById(R.id.seekBar);
        checkBoxYuwen = findViewById(R.id.checkBox1);
        checkBoxShuxue = findViewById(R.id.checkBox2);
        checkBoxYingyu = findViewById(R.id.checkBox3);
        ratingBar = findViewById(R.id.ratingBar);

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("左");
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("右");
            }
        });

    }
}
