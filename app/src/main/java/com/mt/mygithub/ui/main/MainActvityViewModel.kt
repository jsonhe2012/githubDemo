package com.mt.mygithub.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @description:
 * @author: jasonhe .
 * @data:   On 2022/8/17
 */
class MainActvityViewModel : ViewModel() {

    private val _tabIndex = MutableLiveData<Int>().apply {
        value = 0
    }
    val tabIndex: LiveData<Int> = _tabIndex
}