package com.androidhuman.example.simplegithub.rx

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by tura on 2018-08-01.
 */

class AutoClearedDisposable(
    private val lifecycleOwner: AppCompatActivity,
    // onStop()이 호출될 때 항상 clear할까?
    private val alwaysClearOnStop: Boolean = true,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()) : LifecycleObserver {

    fun add(disposable: Disposable) {
        check(lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED))
        compositeDisposable.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun cleanUp() {
        // 항상 clear 하는게 아니라면 액티비티가 종료될 때만 clear를 함
        if (!alwaysClearOnStop && !lifecycleOwner.isFinishing)
            return

        compositeDisposable.clear()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachSelf() {
        compositeDisposable.clear()

        lifecycleOwner.lifecycle.removeObserver(this)
    }
}