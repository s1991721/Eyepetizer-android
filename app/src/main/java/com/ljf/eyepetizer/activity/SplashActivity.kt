package com.ljf.eyepetizer.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.GestureDetector
import android.view.KeyEvent
import android.view.MotionEvent
import com.ljf.eyepetizer.BaseActivity
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.fragment.SplashGuideFragment
import com.ljf.eyepetizer.fragment.SplashScaleFragment
import com.ljf.eyepetizer.utils.SpUtils

/**
 * Created by mr.lin on 2017/12/13.
 * 闪屏
 */
class SplashActivity : BaseActivity() {

    lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        showFragment()
    }

    private fun showFragment() {
        var transaction = supportFragmentManager.beginTransaction()
        var fragment: Fragment
        when (isFirst()) {
            true ->
                fragment = SplashGuideFragment()
            false ->
                fragment = SplashScaleFragment()
        }
        transaction.replace(R.id.container, fragment).commit()
    }

    //首次打开
    private fun isFirst(): Boolean {
        return true
        return SpUtils.getInt(SpUtils.KEY_FIRST_START, 0) == 0
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (gestureDetector != null) {
            return gestureDetector.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right)
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}