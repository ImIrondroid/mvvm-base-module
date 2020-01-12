package com.iron.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

open class BaseDialogFragment<VDB: ViewDataBinding> : DialogFragment() {

    @LayoutRes
    protected open val layoutResId: Int
            = R.layout.fragment_base_dialog

    protected lateinit var viewDataBinding: VDB

    override fun onStart() {
        super.onStart()
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate<VDB>(inflater, layoutResId, container, false)
        return viewDataBinding.root
    }
}