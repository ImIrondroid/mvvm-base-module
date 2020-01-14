package com.iron.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VDB : ViewDataBinding, BVM : BaseViewModel> : Fragment() {

    @LayoutRes
    protected open val layoutResId : Int = 0
    protected open val viewModelId : Int = 0

    protected lateinit var viewDataBinding : VDB
    protected abstract val viewModel : BVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate<VDB>(inflater, layoutResId, container, false).also {
            it.lifecycleOwner = this@BaseFragment
            it.setVariable(viewModelId, viewModel)
        }
        return viewDataBinding.root
    }
}