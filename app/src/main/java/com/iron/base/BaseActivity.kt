package com.iron.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.iron.widget.SimpleLoadingDialog
import kotlin.reflect.KClass

abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity(), BaseNavigator {

    @LayoutRes
    protected open val layoutResId: Int = 0

    protected lateinit var viewDataBinding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
    }

    private fun bindView() {
        viewDataBinding = DataBindingUtil.setContentView<VDB>(this, layoutResId).also {
            it.lifecycleOwner = this
            setBindingVariables()
            it.executePendingBindings()
        }
    }

    protected open fun setBindingVariables() {
        //set variables
    }

    override fun <T : Activity> nextActivity(destination: KClass<T>) {
        startActivity(Intent(this, destination.java))
    }

    override fun previousActivity() {
        onBackPressed()
    }

    override fun showLoading() {
        val existFragment  = supportFragmentManager
            .findFragmentByTag(SimpleLoadingDialog.TAG) as? DialogFragment

        if(existFragment == null) {
            SimpleLoadingDialog.instantiate()
                .show(supportFragmentManager, SimpleLoadingDialog.TAG)
        } else {
            if(!existFragment.showsDialog) {
                existFragment
                    .show(supportFragmentManager, SimpleLoadingDialog.TAG)
            }
        }
    }

    override fun hideLoading() {
        val existFragment = supportFragmentManager
            .findFragmentByTag(SimpleLoadingDialog.TAG) as? SimpleLoadingDialog ?: return

        if(existFragment.showsDialog) {
            existFragment.dismiss()
        }
    }

}