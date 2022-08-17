package com.mt.mygithub.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

/**
 * @description:
 * @author: jasonhe .
 * @data:   On 2022/8/17
 */
open class BaseActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initImmersionBar()
    }


    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected fun initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).init()
    }
}