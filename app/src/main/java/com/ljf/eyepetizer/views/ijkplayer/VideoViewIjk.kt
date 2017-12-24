package com.ljf.eyepetizer.views.ijkplayer

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import android.widget.FrameLayout
import com.ljf.eyepetizer.R
import tv.danmaku.ijk.media.player.IMediaPlayer
import tv.danmaku.ijk.media.player.IjkMediaPlayer

/**
 * Created by mr.lin on 2017/12/22.
 * ijkPlayer控件
 */
class VideoViewIjk(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr), IjkPlayerListener {

    lateinit var surfaceView: SurfaceView
    lateinit var mediaPlayer: IMediaPlayer
    var ijkPlayerListener: IjkPlayerListener? = null

    var mediacodec: Int
    var mediacodec_auto_rotate: Int
    var mediacodec_handle_resolution_change: Int
    var opensles: Int
    var overlay_format: String?
    var framedrop: Int
    var start_on_prepared: Int
    var http_detect_range_support: Int
    var skip_loop_filter: Int

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        var typedArray = context.obtainStyledAttributes(attrs, R.styleable.VideoViewIjk)

        mediacodec = typedArray.getInt(R.styleable.VideoViewIjk_mediacodec, 0)
        mediacodec_auto_rotate = typedArray.getInt(R.styleable.VideoViewIjk_mediacodec_auto_rotate, 0)
        mediacodec_handle_resolution_change = typedArray.getInt(R.styleable.VideoViewIjk_mediacodec_handle_resolution_change, 0)
        opensles = typedArray.getInt(R.styleable.VideoViewIjk_opensles, 0)
        overlay_format = typedArray.getString(R.styleable.VideoViewIjk_overlay_format)
        framedrop = typedArray.getInt(R.styleable.VideoViewIjk_framedrop, 0)
        start_on_prepared = typedArray.getInt(R.styleable.VideoViewIjk_start_on_prepared, 0)
        http_detect_range_support = typedArray.getInt(R.styleable.VideoViewIjk_http_detect_range_support, 0)
        skip_loop_filter = typedArray.getInt(R.styleable.VideoViewIjk_skip_loop_filter, 0)

        typedArray.recycle()

        IjkMediaPlayer.loadLibrariesOnce(null)

        initPlayer()
        initView()
    }

    private fun initPlayer() {
        var player = IjkMediaPlayer()

        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", mediacodec.toLong())
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", mediacodec_auto_rotate.toLong())
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", mediacodec_handle_resolution_change.toLong())
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", opensles.toLong())
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", overlay_format)
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", framedrop.toLong())
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", start_on_prepared.toLong())
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "http-detect-range-support", http_detect_range_support.toLong())
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "skip_loop_filter", skip_loop_filter.toLong())

        player.setOnPreparedListener(this)
        player.setOnVideoSizeChangedListener(this)
        player.setOnCompletionListener(this)
        player.setOnErrorListener(this)
        player.setOnInfoListener(this)
        player.setOnBufferingUpdateListener(this)
        player.setOnSeekCompleteListener(this)

        mediaPlayer = player

    }

    public fun setVideoViewIjkListener(listener: IjkPlayerListener) {
        ijkPlayerListener = listener
    }

    private fun initView() {
        surfaceView = SurfaceView(context)
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                mediaPlayer.setDisplay(holder)
            }
        })

        addView(surfaceView, 0, LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    }

    override fun onDetachedFromWindow() {
        mediaPlayer.setDisplay(null)
        mediaPlayer.release()
        super.onDetachedFromWindow()
    }

    //播放器操作
    fun setDataSource(source: String) {
        mediaPlayer.dataSource = source
        mediaPlayer.prepareAsync()
    }

    fun start() {
        mediaPlayer.start()
    }

    fun stop() {
        mediaPlayer.stop()
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }

    fun seekTo(position: Long) {
        mediaPlayer.seekTo(position)
    }

    fun getCurrentPosition(): Long {
        return mediaPlayer.currentPosition
    }

    fun getDuration(): Long {
        return mediaPlayer.duration
    }

    fun release() {
        mediaPlayer.release()
    }

    fun reset() {
        mediaPlayer.reset()
    }

    fun setVolume(leftVolume: Float, rightVolume: Float) {
        mediaPlayer.setVolume(leftVolume, rightVolume)
    }

    fun setBrightness() {
        TODO("设置亮度")
    }

    //回调
    override fun onPrepared(p0: IMediaPlayer?) {
        ijkPlayerListener?.onPrepared(p0)
    }

    override fun onVideoSizeChanged(p0: IMediaPlayer?, p1: Int, p2: Int, p3: Int, p4: Int) {
        ijkPlayerListener?.onVideoSizeChanged(p0, p1, p2, p3, p4)
    }

    override fun onCompletion(p0: IMediaPlayer?) {
        ijkPlayerListener?.onCompletion(p0)
    }

    override fun onError(p0: IMediaPlayer?, p1: Int, p2: Int): Boolean {
        return ijkPlayerListener?.onError(p0, p1, p2) ?: false
    }

    override fun onInfo(p0: IMediaPlayer?, p1: Int, p2: Int): Boolean {
        return ijkPlayerListener?.onInfo(p0, p1, p2) ?: false
    }

    override fun onBufferingUpdate(p0: IMediaPlayer?, p1: Int) {
        ijkPlayerListener?.onBufferingUpdate(p0, p1)
    }

    override fun onSeekComplete(p0: IMediaPlayer?) {
        ijkPlayerListener?.onSeekComplete(p0)
    }

}


