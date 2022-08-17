package com.mt.mygithub.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mt.mygithub.data.SearchBean

class SearchViewModel : ViewModel() {
    val searChDatas = MutableLiveData<List<SearchBean>>()

    fun searchUser(keywords: String?) {
        keywords?.let {
            val datas = mutableListOf<SearchBean>()

            repeat(5) {
                val bean = SearchBean(it, "模拟搜索结果${it}}")
                datas.add(bean)
            }

            searChDatas.value = datas
        }

    }

}