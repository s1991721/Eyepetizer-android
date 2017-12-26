package com.ljf.eyepetizer.fragment

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import com.ljf.eyepetizer.BaseFragment
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.activity.MainActivity
import com.ljf.eyepetizer.activity.SplashActivity
import com.ljf.eyepetizer.utils.CacheUtils
import com.ljf.eyepetizer.utils.SpUtils
import com.ljf.eyepetizer.views.ijkplayer.SimpleIjkPlayerListener
import kotlinx.android.synthetic.main.fragment_splash_guide.*
import tv.danmaku.ijk.media.player.IMediaPlayer

/**
 * Created by mr.lin on 2017/12/26.
 * 闪屏引导页
 */
class SplashGuideFragment : BaseFragment() {

    lateinit var guideZh: Array<String>
    lateinit var guideEn: Array<String>

    var isResume = false

    var currentPosition = 0

    lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        guideZh = resources.getStringArray(R.array.lead_description_zh)
        guideEn = resources.getStringArray(R.array.lead_description_en)
        gestureDetector = GestureDetector(activity, object : GestureDetector.OnGestureListener {
            override fun onShowPress(e: MotionEvent?) {
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return false
            }

            override fun onDown(e: MotionEvent?): Boolean {
                return false
            }

            override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                var yDifference = e2.y - e1.y
                var xDifference = e2.x - e1.x
                if (Math.abs(xDifference) > Math.abs(yDifference)) {//横向
                    if (xDifference > 0) {//right
                        setPosition(--currentPosition)
                    } else {
                        setPosition(++currentPosition)
                    }
                } else {//纵向
                    if (yDifference > 0) {//down


                    } else {
                        goMainLeft(false)
                    }
                }

                return true
            }

            override fun onLongPress(e: MotionEvent?) {
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                return false
            }
        })
        (activity as SplashActivity).gestureDetector = gestureDetector
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_splash_guide, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        if (!isResume) {
            showSplash()
            isResume = true
        }
    }

    private fun showSplash() {
        videoview_ijk.setVideoViewIjkListener(object : SimpleIjkPlayerListener() {
            override fun onPrepared(p0: IMediaPlayer) {
                p0.start()
            }
        })
        videoview_ijk.setLooping(true)
        videoview_ijk.setDataSource(CacheUtils.getFileDir() + "landing.mp4")

        var typeFace = Typeface.createFromAsset(activity.assets, "Lobster-1.4.otf")
        zh.setContentText(guideZh[currentPosition])
        en.setContentText(guideEn[currentPosition])
        en.typeface = typeFace
    }


    private fun setPosition(position: Int) {
        if (position < 0) {
            currentPosition = 0
            return
        }
        if (currentPosition >= guideZh.size) {
            goMainLeft(true)
            return
        }

        indicator.select(currentPosition)
        zh.setContentText(guideZh[currentPosition])
        en.setContentText(guideEn[currentPosition])
    }

    private fun goMainLeft(boolean: Boolean) {
        SpUtils.setInt(SpUtils.KEY_FIRST_START, 1)
        activity.startActivity(Intent(activity, MainActivity::class.java))
        if (boolean) {
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right)
        } else {
            activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_in_bottom)
        }
        activity.finish()
    }

}