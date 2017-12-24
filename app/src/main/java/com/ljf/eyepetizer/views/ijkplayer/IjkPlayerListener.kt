package com.ljf.eyepetizer.views.ijkplayer

import tv.danmaku.ijk.media.player.IMediaPlayer

/**
 * Created by mr.lin on 2017/12/23.
 * VideoViewIjk 的回调
 */
interface IjkPlayerListener : IMediaPlayer.OnPreparedListener, IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnInfoListener, IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnSeekCompleteListener {
}