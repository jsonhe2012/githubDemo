package com.mt.mygithub.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mt.mygithub.databinding.FragmentDashboardBinding

/**
 * @description:搜索页面
 * @author: jasonhe .
 * @data: On 2022/8/16
 */
class SearChFragment : Fragment() {

    companion object {
        fun newInstance() = SearChFragment()
    }

    private lateinit var mSearchViewModel: SearchViewModel
    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mSearchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        mSearchViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}