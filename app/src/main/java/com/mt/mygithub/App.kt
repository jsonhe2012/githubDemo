package com.mt.mygithub

import android.app.Application
import android.content.res.Configuration
import com.tencent.mmkv.MMKV

/**
 * @description:
 * @author: jasonhe .
 * @data:   On 2022/8/16
 */
class App : Application() {
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    override fun registerActivityLifecycleCallbacks(callback: ActivityLifecycleCallbacks?) {
        super.registerActivityLifecycleCallbacks(callback)
    }
}