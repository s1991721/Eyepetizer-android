package com.ljf.eyepetizer.views.ijkplayer

import tv.danmaku.ijk.media.player.IMediaPlayer

/**
 * Created by mr.lin on 2017/12/23.
 * 选择使用SimpleIjkPlayerListener
 * 可以避免直接实现接口而重写全部方法
 */
open class SimpleIjkPlayerListener : IjkPlayerListener {
    override fun onPrepared(p0: IMediaPlayer) {
    }

    override fun onVideoSizeChanged(p0: IMediaPlayer, p1: Int, p2: Int, p3: Int, p4: Int) {
    }

    override fun onCompletion(p0: IMediaPlayer) {
    }

    override fun onError(p0: IMediaPlayer, p1: Int, p2: Int): Boolean {
        return false
    }

    override fun onInfo(p0: IMediaPlayer, p1: Int, p2: Int): Boolean {
        return false
    }

    override fun onBufferingUpdate(p0: IMediaPlayer, p1: Int) {
    }

    override fun onSeekComplete(p0: IMediaPlayer) {
    }
}