package com.iron.base

import android.app.Activity
import android.os.Bundle
import kotlin.reflect.KClass

interface BaseNavigator {

    fun backActivity()
    fun <T: Activity> nextActivity(kClass: KClass<T>, bundle: Bundle? = null, clearTask: Boolean = false)

    fun showLoading()
    fun hideLoading()
}