package com.mt.mygithub.ui.main.home.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mt.mygithub.R
import com.mt.mygithub.data.HomeBean

/**
 * @description:
 * @author: jasonhe .
 * @data:   On 2022/8/17
 */
class HomeAdapter(var datas: MutableList<HomeBean> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null

        when (viewType) {
            0 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_home_title_item, parent, false)

                viewHolder = TitleHolder(view)
            }
            1 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_home_work_item, parent, false)

                viewHolder = WorkHolder(view)
            }
            2 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_collect_item, parent, false)

                viewHolder = CollectHolder(view)
            }
            4 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_hot_child_item, parent, false)

                viewHolder = HotHolder(view)
            }
            5 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_hot_child_child_item, parent, false)

                viewHolder = HotChildHolder(view)
            }
            3 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_profile_list_item, parent, false)

                viewHolder = ProfileList(view)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val testBean = datas.get(position)
        when (testBean.dataType) {
            0 -> {
            }
            1 -> {
                (holder as WorkHolder).tvView.text = testBean.title
                holder.ivView.setImageResource(testBean.imgResId)
            }
            //
            4 -> {
                //
                (holder as HotHolder).recyclerView.adapter ?: let {
                    var childAdapter = HomeAdapter(mutableListOf())
                    holder.recyclerView.adapter = childAdapter
                    val manager = LinearLayoutManager(holder.itemView.context)
                    manager.orientation = LinearLayoutManager.HORIZONTAL
                    holder.recyclerView.layoutManager = manager
                    holder.recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                        override fun getItemOffsets(
                            outRect: Rect,
                            view: View,
                            parent: RecyclerView,
                            state: RecyclerView.State
                        ) {
                            outRect.right = 20
                        }
                    })
                    childAdapter.updateData(testBean.childList)
                }
            }
            3 -> {
                (holder as ProfileList).recyclerView.adapter ?: let {
                    var childAdapter = HomeAdapter(mutableListOf())
                    holder.recyclerView.adapter = childAdapter
                    val manager = LinearLayoutManager(holder.itemView.context)
                    manager.orientation = LinearLayoutManager.VERTICAL
                    holder.recyclerView.layoutManager = manager
                    childAdapter.updateData(testBean.childList)
                }
            }
        }
    }

    fun updateData(tempdata: List<HomeBean>?) {
        datas.clear()
        tempdata?.let { datas.addAll(it) }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        val testBean = datas.get(position)
        return testBean.dataType
    }
}

class TitleHolder : RecyclerView.ViewHolder {
    lateinit var tvView: TextView

    constructor(itemView: View) : super(itemView) {
        tvView = itemView.findViewById(R.id.tv_title)
    }
}

class WorkHolder : RecyclerView.ViewHolder {
    lateinit var tvView: TextView
    lateinit var ivView: ImageView

    constructor(itemView: View) : super(itemView) {
        tvView = itemView.findViewById(R.id.tv_title)
        ivView = itemView.findViewById(R.id.iv_image)
    }
}


class CollectHolder : RecyclerView.ViewHolder {
    lateinit var tvView: TextView

    constructor(itemView: View) : super(itemView) {
        tvView = itemView.findViewById(R.id.tv_title)
    }
}


class HotHolder : RecyclerView.ViewHolder {
    lateinit var recyclerView: RecyclerView

    constructor(itemView: View) : super(itemView) {
        recyclerView = itemView.findViewById(R.id.recycleview)
    }
}


class HotChildHolder : RecyclerView.ViewHolder {

    constructor(itemView: View) : super(itemView) {
    }
}


class ProfileList : RecyclerView.ViewHolder {
    lateinit var recyclerView: RecyclerView

    constructor(itemView: View) : super(itemView) {
        recyclerView = itemView.findViewById(R.id.recycleview)
    }
}