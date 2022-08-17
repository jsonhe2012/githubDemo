package com.mt.mygithub.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mt.mygithub.R
import com.mt.mygithub.ui.login.ui.login.LoginActivity
import com.mt.mygithub.ui.main.MainActivity
import com.mt.mygithub.ui.wigit.dialog.ProgressDialog
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var userName = MMKV.defaultMMKV()?.getString("user_name", "");
        var userPassword = MMKV.defaultMMKV()?.getString("user_password", "")
        var isBlan = userName?.isNotBlank()
        userName?.let {
            it.takeIf { it.isNotBlank() }?.let {
                gotoMain()
            } ?: let {
                goLogin()
            }
        } ?: let {
            goLogin()
        }
    }

    val loadingDialog by lazy { ProgressDialog() }
    fun gotoMain() {
        loadingDialog.show(supportFragmentManager,"loadingDialog")
        MainScope().launch {
            delay(1500)
            var intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            loadingDialog.dismiss()
            finish()
        }
    }

    fun goLogin() {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}