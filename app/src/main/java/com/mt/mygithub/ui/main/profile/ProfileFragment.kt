package com.mt.mygithub.ui.main.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mt.mygithub.databinding.ProfileFragmentBinding
import com.mt.mygithub.ui.main.home.adapter.HomeAdapter

/**
 * @description:个人页面
 * @author: jasonhe .
 * @data: On 2022/8/16
 */
class ProfileFragment : Fragment() {

    var profileAdapter: HomeAdapter? = null

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding?.apply {
            val manager = LinearLayoutManager(context)
            manager.orientation = LinearLayoutManager.VERTICAL
            recycleview.layoutManager = manager

            profileAdapter = HomeAdapter(mutableListOf())
            recycleview.adapter = profileAdapter
        }
        viewModel.datas.observe(this, {
            profileAdapter?.updateData(it)
        })
        viewModel.buildData()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}