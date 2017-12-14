package com.ljf.eyepetizer.activity

import android.content.Intent
import android.os.Bundle
import com.ljf.eyepetizer.BaseActivity
import com.ljf.eyepetizer.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by mr.lin on 2017/12/13.
 * 闪屏
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        goMain()
    }

    private fun goMain() {
        textView.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }

}