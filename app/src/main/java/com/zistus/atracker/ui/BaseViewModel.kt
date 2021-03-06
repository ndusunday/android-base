package com.zistus.atracker.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel: ViewModel() {
    private val mDisposables = CompositeDisposable()
    /**
     * An Extended Disposable to add the disposable to the compositeDisposable
     */
    protected fun Disposable.track() {
        mDisposables.add(this)
    }

    override fun onCleared() {
        mDisposables.clear()
        super.onCleared()
        mDisposables.dispose()
    }

//    protected fun <T> MediatorLiveData<T>.add(publisher: Publisher<T>) {
//        addSource(LiveDataReactiveStreams.fromPublisher(publisher)) {
//            postValue(it)
//        }
//    }
}