package com.supremesir.uidemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
//import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.format;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "my_log";

    TextView display;
    Button buttonLeft, buttonRight, buttonConfirm;
    ImageButton buttonThumbUp, buttonThumbDown;
    Switch aSwitch;
    ProgressBar progressBar;
    EditText editText;
    RadioGroup radioGroup;
    ImageView imageView;
    SeekBar seekBar;
    CheckBox checkBoxYuwen, checkBoxShuxue, checkBoxYingyu;
    RatingBar ratingBar;
    String yuwen = "";
    String shuxue = "";
    String yingyu = "";
    MyViewModel myViewModel;
    MyViewModelWithLiveData myViewModelWithLiveData;

    /**
     * 保存textView状态，当使用ViewModel时，不需要使用该方法保存状态
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("KEY", display.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.textView);
        buttonLeft = findViewById(R.id.button);
        buttonRight = findViewById(R.id.button2);
        buttonConfirm = findViewById(R.id.button3);
        buttonThumbUp = findViewById(R.id.imageButton);
        buttonThumbDown = findViewById(R.id.imageButton2);
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

//        // ViewModelProviders已被弃用
//        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModelWithLiveData = new ViewModelProvider(this).get(MyViewModelWithLiveData.class);
        myViewModelWithLiveData.getLikedNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                display.setText(format("LikedNum: %s", String.valueOf(integer)));
            }
        });


        // 使用ViewModel的方式恢复状态
        display.setText(String.valueOf(myViewModel.num));

        // 当存在保存的状态时，读取状态并显示其中的数据
        if (savedInstanceState != null) {
            String s = savedInstanceState.getString("KEY");
            display.setText(s);
        }

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++myViewModel.num;
                display.setText(format("num: %s", String.valueOf(myViewModel.num)));
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.num += 2;
                display.setText(format("num: %s", String.valueOf(myViewModel.num)));
            }
        });

        // 使用LiveData的方式处理按键相应

        buttonThumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModelWithLiveData.addLikedNumber(1);
            }
        });
        buttonThumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModelWithLiveData.addLikedNumber(-1);
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    display.setText(R.string.open);
                } else {
                    display.setText(R.string.close);
                }
            }
        });
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                // 判断字符串是否为空串，若是，置为0
                if (TextUtils.isEmpty(s)) {
                    s = "0";
                }
                progressBar.setProgress(Integer.valueOf(s),true);
                display.setText(s);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton1) {
                    imageView.setImageResource(R.drawable.icon_android);
                } else {
                    imageView.setImageResource(R.drawable.icon_apple);
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                display.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        checkBoxYuwen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yuwen = getResources().getString(R.string.checkbox1);
                } else {
                    yuwen = "";
                }
                display.setText(format("%s%s%s", yuwen, shuxue, yingyu));
            }
        });
        checkBoxShuxue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    shuxue = getResources().getString(R.string.checkbox2);
                } else {
                    shuxue = "";
                }
                display.setText(format("%s%s%s", yuwen, shuxue, yingyu));
            }
        });
        checkBoxYingyu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yingyu = getResources().getString(R.string.checkbox3);
                } else {
                    yingyu = "";
                }
                display.setText(format("%s%s%s", yuwen, shuxue, yingyu));
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this, rating + "星评价！", Toast.LENGTH_SHORT).show();
            }
        });




        Log.d(TAG, "onCreate: ");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
