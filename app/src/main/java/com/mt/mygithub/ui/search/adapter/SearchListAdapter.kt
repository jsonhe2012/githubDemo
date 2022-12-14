package com.mt.mygithub.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.mt.mygithub.R
import com.mt.mygithub.data.SearchBean

/**
 * @description:
 * @author: jasonhe .
 * @data:   On 2022/8/17
 */
class SearchListAdapter : BaseAdapter {
    constructor() : super()

    private var list = mutableListOf<SearchBean>()

    private var context: Context? = null

    constructor(context: Context, list: List<SearchBean>?) : super() {
        list?.let {
            this.list.addAll(it)
        }
        this.context = context
    }

    override fun getCount(): Int {
        return list?.let {
            return it.size
        } ?: 0
    }

    override fun getItem(p0: Int): Any {
        return 1
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {
        var convertView = view
        var viewHolder: ViewHolder? = null
        var seatChBean = list?.get(position)
        convertView?.let {
            viewHolder = convertView?.getTag() as ViewHolder?
            viewHolder ?: let {
                viewHolder = ViewHolder()
            }
        } ?: let {
            convertView =
                LayoutInflater.from(parent?.context).inflate(R.layout.serach_list_item, null)
            viewHolder = ViewHolder()
            convertView?.setTag(viewHolder)
        }

        viewHolder?.title?.text = seatChBean?.title ?: ""

        return convertView
    }

    fun updateData(datas: List<SearchBean>?) {
        list.clear()
        datas?.let {
            this.list.addAll(it)
        }
        notifyDataSetChanged()
    }

}

internal class ViewHolder {
    var title: TextView? = null
}