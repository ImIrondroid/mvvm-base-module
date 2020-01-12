package com.iron.widget

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.iron.base.R

class SimpleLoadingDialog : DialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.dialog_simple_loading, container)
        return view
    }

    companion object {
        const val TAG = "SimpleLoadingProgess"
        fun instantiate() : SimpleLoadingDialog {
            return SimpleLoadingDialog()
        }
    }
}