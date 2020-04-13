package com.iron.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.iron.widget.SimpleLoadingDialogFragment
import java.io.Serializable
import kotlin.reflect.KClass

abstract class BaseActivity<VDB : ViewDataBinding, VM: BaseViewModel<*>> : AppCompatActivity(), BaseNavigator {

    @LayoutRes
    protected open val layoutResId: Int = 0
    protected open val viewModelId: Int = BR.viewModel

    protected abstract val viewModel: VM
    protected lateinit var viewDataBinding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerNavigator()
        bindView()
    }

    private fun bindView() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResId)
        viewDataBinding.apply {
            lifecycleOwner = this@BaseActivity
            setBindingVariables()
            executePendingBindings()
        }
    }

    protected open fun registerNavigator() {}

    protected open fun setBindingVariables() {
        viewDataBinding.setVariable(viewModelId, viewModel)
        //set variables
    }

    override fun <T : Activity> nextActivity(kClass: KClass<T>, bundle: Bundle?, clearTask: Boolean) {
        startActivity(Intent(this, kClass.java).apply {
            bundle?.let(this::putExtras)
            if(clearTask) {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            }
        })
    }

    override fun backActivity() {
        onBackPressed()
    }

    override fun showLoading() {
        val existFragment  = supportFragmentManager
            .findFragmentByTag(SimpleLoadingDialogFragment.TAG) as? DialogFragment

        if(existFragment == null) {
            SimpleLoadingDialogFragment.instantiate()
                .show(supportFragmentManager, SimpleLoadingDialogFragment.TAG)
        } else {
            if(!existFragment.showsDialog) {
                existFragment
                    .show(supportFragmentManager, SimpleLoadingDialogFragment.TAG)
            }
        }
    }

    override fun hideLoading() {
        val existFragment = supportFragmentManager
            .findFragmentByTag(SimpleLoadingDialogFragment.TAG) as? SimpleLoadingDialogFragment ?: return

        if(existFragment.showsDialog) {
            existFragment.dismiss()
        }
    }
}