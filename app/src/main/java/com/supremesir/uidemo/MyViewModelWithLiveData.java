package com.supremesir.uidemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author HaoFan Fang
 * @date 2020-02-24 10:49
 */

public class MyViewModelWithLiveData extends ViewModel {

    private MutableLiveData<Integer> likedNumber;

    // 构造函数也必须为public
    public MyViewModelWithLiveData() {
        // 避免LikedNumber为空
        likedNumber = new MutableLiveData<>(0);
//        likedNumber = new MutableLiveData<>();
//        likedNumber.setValue(0);
    }

    public MutableLiveData<Integer> getLikedNumber() {
//        // 懒加载，貌似有并发问题
//        if (likedNumber == null) {
//            likedNumber = new MutableLiveData<>(0);
//        }
        return likedNumber;
    }

    public void setLikedNumber(MutableLiveData<Integer> likedNumber) {
        likedNumber = likedNumber;
    }

    public void addLikedNumber(int n) {
        likedNumber.setValue(likedNumber.getValue() + n);
    }
}
