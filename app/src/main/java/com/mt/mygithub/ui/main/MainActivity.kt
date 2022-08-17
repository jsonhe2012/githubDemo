package com.mt.mygithub.ui.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.mt.mygithub.R
import com.mt.mygithub.databinding.ActivityMainBinding
import com.mt.mygithub.ui.BaseActivity
import com.mt.mygithub.ui.main.home.HomeFragment
import com.mt.mygithub.ui.main.notifications.NotificationsFragment
import com.mt.mygithub.ui.main.profile.ProfileFragment
import com.mt.mygithub.ui.main.search.SearChFragment
import com.mt.mygithub.utils.FragmentSwitchTool


/**
 * @description:首页
 * @author: jasonhe .
 * @data: On 2022/8/16
 */
class MainActivity : BaseActivity() {
    var fragmentSwitchTool: FragmentSwitchTool? = null
    private lateinit var binding: ActivityMainBinding
    var mainActvityViewModel: MainActvityViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            )
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.TRANSPARENT)
        } else if (Build.VERSION.SDK_INT >= 19) { //19表示4.4
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainActvityViewModel = ViewModelProviders.of(this).get(MainActvityViewModel::class.java)
        mainActvityViewModel?.tabIndex?.observe(this, {
            onTabChange(it)
        })
        binding?.let {
            fragmentSwitchTool =
                FragmentSwitchTool.Builder().fragmentManager(supportFragmentManager)
                    .containerId(R.id.layout_container).clickableViews(
                        it.layoutHome,
                        it.layoutNotifications,
                        it.layoutSearch,
                        it.layoutProfile
                    )
                    .fragments(
                        HomeFragment(),
                        NotificationsFragment(),
                        SearChFragment(),
                        ProfileFragment()
                    )
                    .build()
            fragmentSwitchTool?.changeTag(it.layoutHome)
        }
    }


    fun onTabChange(tabIndex: Int) {
        when (tabIndex) {
            0 -> {
                fragmentSwitchTool?.changeTag(binding?.layoutHome)
            }
            1 -> {
                fragmentSwitchTool?.changeTag(binding?.layoutNotifications)
            }
            2 -> {
                fragmentSwitchTool?.changeTag(binding?.layoutSearch)
            }
            3 -> {
                fragmentSwitchTool?.changeTag(binding?.layoutProfile)

            }
        }
    }
}