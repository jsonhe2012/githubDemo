package com.mt.mygithub.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mt.mygithub.databinding.FragmentHomeBinding
import com.mt.mygithub.ui.main.home.adapter.HomeAdapter
import com.mt.mygithub.ui.search.SearchFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @description:主页页面
 * @author: jasonhe .
 * @data: On 2022/8/16
 */
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val searchFragment: SearchFragment by lazy {
        SearchFragment()
    }
    var homeAdapter: HomeAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.datas.observe(viewLifecycleOwner, Observer {
            homeAdapter?.updateData(it)
        })

        _binding?.apply {
            val manager = LinearLayoutManager(context)
            manager.orientation = LinearLayoutManager.VERTICAL
            recycleview.layoutManager = manager

            homeAdapter = HomeAdapter(mutableListOf())
            recycleview.adapter = homeAdapter
            ivSearch.setOnClickListener({
                searchFragment.show(activity!!.supportFragmentManager, "searchFragment")
            })
        }
        homeViewModel.buildData()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}