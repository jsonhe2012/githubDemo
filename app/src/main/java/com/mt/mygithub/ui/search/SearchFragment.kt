package com.mt.mygithub.ui.search

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.mt.mygithub.R
import com.mt.mygithub.databinding.SearchFragmentBinding
import com.mt.mygithub.ui.search.adapter.SearchListAdapter
import kotlinx.android.synthetic.main.search_fragment.*


class SearchFragment : DialogFragment(), View.OnClickListener, OnEditorActionListener {
    private var viewBinding: SearchFragmentBinding? = null
    var searchAdapter: SearchListAdapter? = null

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onStart() {
        super.onStart()

        var window = dialog?.window
        val windowParams = window!!.attributes
        windowParams.dimAmount = 0.0f;//Dialog外边框透明

        window.setLayout(-1, -2); //高度自适应，宽度全屏

        windowParams.gravity = Gravity.TOP; //在顶部显示
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT

        windowParams.height = WindowManager.LayoutParams.MATCH_PARENT
        window.setAttributes(windowParams)
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (context == null) {
            return super.onCreateDialog(savedInstanceState)
        }
        return Dialog(requireContext(), R.style.fullDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = SearchFragmentBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        initView()
        viewModel?.searChDatas?.observe(this, {
            if (it.size > 0) {
                viewBinding?.searchList?.visibility = View.VISIBLE
            } else {
                viewBinding?.searchList?.visibility = View.GONE
            }

            searchAdapter?.updateData(it)
        })
    }


    private fun initView() {
        viewBinding?.run {
            input.setOnEditorActionListener(this@SearchFragment)
            searchbtn.setOnClickListener(this@SearchFragment)
            input.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                override fun afterTextChanged(editable: Editable) {
                    if (input.text.toString().length < 1) {
                        searchNull.setVisibility(View.VISIBLE)
                        searchList.setVisibility(View.GONE)
                        lineView.setVisibility(View.GONE)
                    }
                }
            })
            input.requestFocus()

            searchAdapter = SearchListAdapter()
            searchList.adapter = searchAdapter
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.searchbtn -> {
                setInputShow(false, context!!, input)
                dismiss()
            }
        }
    }

    override fun onEditorAction(textView: TextView?, p1: Int, p2: KeyEvent?): Boolean {
        if (p1 == EditorInfo.IME_ACTION_SEARCH) {
            if (!TextUtils.isEmpty(textView?.getText().toString())) {
                searchUser(textView?.getText().toString())
            }
        }
        return true
    }

    fun searchUser(keywords: String?) {
        viewModel?.searchUser(keywords)
    }


    /**
     * 设置输入法展现和关闭
     *
     * @param bShow
     * @param context
     * @param view
     */
    fun setInputShow(bShow: Boolean, context: Context, view: View) {
        if (bShow) {
            view.requestFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.showSoftInput(view, 0)
        } else {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}