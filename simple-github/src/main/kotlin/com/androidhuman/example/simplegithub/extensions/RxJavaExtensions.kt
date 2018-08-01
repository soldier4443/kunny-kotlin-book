package com.androidhuman.example.simplegithub.extensions

import com.androidhuman.example.simplegithub.rx.AutoClearedDisposable
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by tura on 2018-08-01.
 */

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

operator fun AutoClearedDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

fun runOnIoScheduler(function: () -> Unit): Disposable {
    return Completable
        .fromCallable(function)
        .subscribeOn(Schedulers.io())
        .subscribe()
}
