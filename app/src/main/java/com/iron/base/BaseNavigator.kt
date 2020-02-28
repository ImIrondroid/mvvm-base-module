package com.iron.base

import android.app.Activity
import kotlin.reflect.KClass

interface BaseNavigator {

    fun <T: Activity> nextActivity(destination: KClass<T>)
    fun <T: Activity> nextActivityWithSerializableData(destination : KClass<T>)
    fun previousActivity()

    fun showLoading()
    fun hideLoading()
}