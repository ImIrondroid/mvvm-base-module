package com.iron.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {
    protected val compositeDisposable : CompositeDisposable by lazy {
        CompositeDisposable()
    }

    protected fun addAllDisposable(vararg disposables: Disposable) {
        for(disposable in disposables)
            disposable
                .let(compositeDisposable::add)

    }

    protected fun addDisposable(disposable: Disposable) {
        disposable
            .let(compositeDisposable::add)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}