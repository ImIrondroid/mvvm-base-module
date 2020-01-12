package com.iron.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.iron.base.R

class BaseDialogFragment : DialogFragment() {

    @LayoutRes
    protected open val layoutResId: Int
            = R.layout.dialog_simple_loading

    override fun onStart() {
        super.onStart()
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(layoutResId, container)
        return view
    }

    companion object {
        const val TAG = "SimpleLoadingProgess"
        fun instantiate() : BaseDialogFragment {
            return BaseDialogFragment()
        }
    }
}