package com.mt.mygithub.data

import java.io.Serializable

/**
 * @description:
 * @author: jasonhe .
 * @data:   On 2022/8/17
 */
data class HomeBean(
    val dataType: Int,
    val title: String,
    val imgResId: Int
) : Serializable {
    var childList: MutableList<HomeBean>? = null
}
