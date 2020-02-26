package com.supremesir.uidemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
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

import com.supremesir.uidemo.databinding.ActivityMainBinding;

import static java.lang.String.format;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "my_log";

    ActivityMainBinding binding;

//    TextView display;
//    Button buttonLeft, buttonRight, buttonConfirm;
//    ImageButton buttonThumbUp, buttonThumbDown;
//    Switch aSwitch;
//    ProgressBar progressBar;
//    EditText editText;
//    RadioGroup radioGroup;
//    ImageView imageView;
//    SeekBar seekBar;
//    CheckBox checkBoxYuwen, checkBoxShuxue, checkBoxYingyu;
//    RatingBar ratingBar;

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
        outState.putString("KEY", binding.textView.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        display = findViewById(R.id.textView);
//        buttonLeft = findViewById(R.id.button);
//        buttonRight = findViewById(R.id.button2);
//        buttonConfirm = findViewById(R.id.button3);
//        buttonThumbUp = findViewById(R.id.imageButton);
//        buttonThumbDown = findViewById(R.id.imageButton2);
//        aSwitch = findViewById(R.id.switch1);
//        progressBar = findViewById(R.id.progressBar3);
//        editText = findViewById(R.id.editText2);
//        radioGroup = findViewById(R.id.radioGroup1);
//        imageView = findViewById(R.id.imageView);
//        seekBar = findViewById(R.id.seekBar);
//        checkBoxYuwen = findViewById(R.id.checkBox1);
//        checkBoxShuxue = findViewById(R.id.checkBox2);
//        checkBoxYingyu = findViewById(R.id.checkBox3);
//        ratingBar = findViewById(R.id.ratingBar);

//        // ViewModelProviders已被弃用
//        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModelWithLiveData = new ViewModelProvider(this).get(MyViewModelWithLiveData.class);
        myViewModelWithLiveData.getLikedNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textView.setText(format("LikedNum: %s", String.valueOf(integer)));
            }
        });


        // 使用ViewModel的方式恢复状态
        binding.textView.setText(String.valueOf(myViewModel.num));

        // 当存在保存的状态时，读取状态并显示其中的数据
        if (savedInstanceState != null) {
            String s = savedInstanceState.getString("KEY");
            binding.textView.setText(s);
        }

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++myViewModel.num;
                binding.textView.setText(format("num: %s", String.valueOf(myViewModel.num)));
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.num += 2;
                binding.textView.setText(format("num: %s", String.valueOf(myViewModel.num)));
            }
        });

        // 使用LiveData的方式处理按键相应
        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModelWithLiveData.addLikedNumber(1);
            }
        });
        binding.imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModelWithLiveData.addLikedNumber(-1);
            }
        });

        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.textView.setText(R.string.open);
                } else {
                    binding.textView.setText(R.string.close);
                }
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = binding.editText2.getText().toString();
                // 判断字符串是否为空串，若是，置为0
                if (TextUtils.isEmpty(s)) {
                    s = "0";
                }
                binding.progressBar3.setProgress(Integer.valueOf(s),true);
                binding.textView.setText(s);
            }
        });
        binding.radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton1) {
                    binding.imageView.setImageResource(R.drawable.icon_android);
                } else {
                    binding.imageView.setImageResource(R.drawable.icon_apple);
                }
            }
        });
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yuwen = getResources().getString(R.string.checkbox1);
                } else {
                    yuwen = "";
                }
                binding.textView.setText(format("%s%s%s", yuwen, shuxue, yingyu));
            }
        });
        binding.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    shuxue = getResources().getString(R.string.checkbox2);
                } else {
                    shuxue = "";
                }
                binding.textView.setText(format("%s%s%s", yuwen, shuxue, yingyu));
            }
        });
        binding.checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yingyu = getResources().getString(R.string.checkbox3);
                } else {
                    yingyu = "";
                }
                binding.textView.setText(format("%s%s%s", yuwen, shuxue, yingyu));
            }
        });
        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
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
