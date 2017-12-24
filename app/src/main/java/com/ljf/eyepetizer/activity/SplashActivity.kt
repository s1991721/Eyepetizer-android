package com.ljf.eyepetizer.activity

import android.os.Bundle
import com.ljf.eyepetizer.BaseActivity
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CacheUtils
import com.ljf.eyepetizer.utils.SpUtils
import com.ljf.eyepetizer.views.ijkplayer.SimpleIjkPlayerListener
import kotlinx.android.synthetic.main.activity_splash.*
import tv.danmaku.ijk.media.player.IMediaPlayer

/**
 * Created by mr.lin on 2017/12/13.
 * 闪屏
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        goMain()
    }

    private fun goMain() {
        if (isFirst()) {
            showSplash()
            SpUtils.setInt(SpUtils.KEY_FIRST_START, 1)
        } else {
        }
    }

    //首次打开
    private fun isFirst(): Boolean {
        return true
//        return SpUtils.getInt(SpUtils.KEY_FIRST_START, 0) == 0
    }

    private fun showSplash() {

//        TODO("onResume 后崩溃")
        videoview_ijk.setVideoViewIjkListener(object : SimpleIjkPlayerListener() {
            override fun onPrepared(p0: IMediaPlayer) {
                p0.start()
            }
        })
        videoview_ijk.setLooping(true)
        videoview_ijk.setDataSource(CacheUtils.getFileDir() + "landing.mp4")
    }

}