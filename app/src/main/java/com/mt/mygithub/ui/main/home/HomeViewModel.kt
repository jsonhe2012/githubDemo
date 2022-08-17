package com.mt.mygithub.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mt.mygithub.R
import com.mt.mygithub.data.HomeBean

/**
 * @description:主页页面
 * @author: jasonhe .
 * @data: On 2022/8/16
 */
class HomeViewModel : ViewModel() {

    val datas = MutableLiveData<List<HomeBean>>()

    fun buildData(){
        val tempDatas = mutableListOf<HomeBean>()
        val bean1 = HomeBean(0,"",0)
        tempDatas.add(bean1)
        val bean2 = HomeBean(1,"议题", R.drawable.icon_gift)
        tempDatas.add(bean2)
        val bean3 = HomeBean(1,"拉取请求", R.drawable.icon_message_inform)
        tempDatas.add(bean3)
        val bean4 = HomeBean(1,"讨论", R.drawable.icon_message_zaixiankefu)
        tempDatas.add(bean4)
        val bean5 = HomeBean(1,"仓库",R.drawable.icon_messages_dtgc)
        tempDatas.add(bean5)
        val bean6 = HomeBean(2,"",0)
        tempDatas.add(bean6)
        datas.value = tempDatas
    }
}