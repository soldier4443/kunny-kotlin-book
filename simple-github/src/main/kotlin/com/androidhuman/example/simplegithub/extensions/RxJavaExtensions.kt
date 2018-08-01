package com.androidhuman.example.simplegithub.extensions

import com.androidhuman.example.simplegithub.ui.AutoClearedDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by tura on 2018-08-01.
 */

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

operator fun AutoClearedDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}