package com.example.week6

import android.media.MediaPlayer
import android.media.AudioAttributes

import kotlinx.coroutines.*

class AudioPlayer {

    private val mediaPlayer = MediaPlayer()
    private var isPrepared = false
    private var isPlaying = false
    private var currentTime=0;

    fun play(url: String) {
        stop()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        mediaPlayer.setAudioAttributes(
                             AudioAttributes
                            .Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build());
        mediaPlayer.setOnPreparedListener {
            isPrepared = true
            mediaPlayer.start()
            isPlaying = true
        }
    }

    fun pause() {
        if (isPlaying) {
            mediaPlayer.pause()
            currentTime=mediaPlayer.getCurrentPosition();
            isPlaying = false
        }
    }

    fun resume() {
        if (!isPlaying) {
            mediaPlayer.seekTo(currentTime);
            mediaPlayer.start();
            isPlaying = true
        }
    }

    fun stop() {
        mediaPlayer.stop()
        mediaPlayer.reset()
        isPrepared = false
        isPlaying = false
    }
}
