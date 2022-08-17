package com.mt.mygithub.ui.wigit.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import com.mt.mygithub.R
import com.mt.mygithub.databinding.LoadingItemBinding

/**
 * @description:
 * @author: jasonhe .
 * @data:   On 2022/8/16
 */
class ProgressDialog : DialogFragment() {
    var viewBinding: LoadingItemBinding? = null
    var progressBar: ProgressBar? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (context == null) {
            return super.onCreateDialog(savedInstanceState)
        }
        return Dialog(requireContext(), R.style.fullDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = LoadingItemBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    open fun show() {
        progressBar?.let {
        }
    }
}