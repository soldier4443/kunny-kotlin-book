package com.androidhuman.example.simplegithub.extensions

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver

/**
 * Created by tura on 2018-08-01.
 */

operator fun Lifecycle.plusAssign(observer: LifecycleObserver) {
    addObserver(observer)
}