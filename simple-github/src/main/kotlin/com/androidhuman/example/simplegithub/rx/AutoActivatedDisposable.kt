package com.androidhuman.example.simplegithub.rx

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.Disposable

/**
 * Created by tura on 2018-08-02.
 *
 * 여기서 disposable이 아니라 () -> Disposable을 받는 이유는..?
 * 잘 모르겠음.. 여기서는 lazy하게 받는게 좋은거같아 보이긴 하는데.. 확신은 x
 */
class AutoActivatedDisposable(private val lifecycleOwner: LifecycleOwner,
                              private val func: () -> Disposable) : LifecycleObserver {

    private var disposable: Disposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activate() {
        // disposable로부터 이벤트를 받기 시작..
        disposable = func()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun deactivate() {
        disposable?.dispose()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachSelf() {
        lifecycleOwner.lifecycle.removeObserver(this)
    }
}