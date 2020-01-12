package com.iron.widget

import com.iron.base.BaseDialogFragment
import com.iron.base.R
import com.iron.base.databinding.FragmentBaseDialogBinding

class SimpleLoadingDialogFragment : BaseDialogFragment<FragmentBaseDialogBinding>() {

    override val layoutResId: Int
        get() = R.layout.fragment_base_dialog

    companion object {
        const val TAG = "SimpleLoadingDialogFragment"
        fun instantiate() : SimpleLoadingDialogFragment {
            return SimpleLoadingDialogFragment()
        }
    }
}