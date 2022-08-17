package com.mt.mygithub.ui.main.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mt.mygithub.R
import com.mt.mygithub.data.HomeBean

/**
 * @description:个人页面
 * @author: jasonhe .
 * @data: On 2022/8/16
 */
class ProfileViewModel : ViewModel() {

    val datas = MutableLiveData<List<HomeBean>>()

    fun buildData() {
        val tempDatas = mutableListOf<HomeBean>()
        val bean2 = HomeBean(4, "", R.drawable.icon_gift)
        bean2.childList = mutableListOf<HomeBean>()
        tempDatas.add(bean2)

        val bean33 = HomeBean(5, "", R.drawable.icon_message_inform)
        bean2.childList?.add(bean33)
        val bean43 = HomeBean(5, "", R.drawable.icon_message_zaixiankefu)
        bean2.childList?.add(bean43)
        val bean63 = HomeBean(5, "", R.drawable.icon_message_inform)
        bean2.childList?.add(bean63)


        val bean1 = HomeBean(3, "", 0)
        bean1.childList = mutableListOf<HomeBean>()
        tempDatas.add(bean1)

        val bean3 = HomeBean(1, "仓库", R.drawable.icon_message_inform)
        bean1.childList?.add(bean3)
        val bean4 = HomeBean(1, "组织", R.drawable.icon_message_zaixiankefu)
        bean1.childList?.add(bean4)
        val bean6 = HomeBean(1, "已加星标", R.drawable.icon_message_inform)
        bean1.childList?.add(bean6)

        datas.value = tempDatas
    }
}