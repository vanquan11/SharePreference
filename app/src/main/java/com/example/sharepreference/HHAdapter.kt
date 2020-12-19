package com.example.sharepreference

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemhh.view.*

class HHAdapter(private val hhList: List<Result>, private  val longClick : (View, Result) -> Unit) : RecyclerView.Adapter<HHAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.itemhh, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.txtId.text = hhList[position].id.toString()
        viewHolder.itemView.txtName.text =  hhList[position].name
        viewHolder.itemView.txtPrice.text =  hhList[position].price.toString()
        viewHolder.itemView.txtPriceLarg.text =  hhList[position].priceLargeUnit.toString()
        viewHolder.itemView.txtcreateDate.text =   hhList[position].createdDate
        viewHolder.itemView.txtModifDate.text =  hhList[position].modifiedDate
        viewHolder.itemView.txtMaxQuan.text =  hhList[position].maxQuantity.toString()
        viewHolder.itemView.txtUnit.text =  hhList[position].unit


        viewHolder.itemView.setOnLongClickListener {
            longClick(it, hhList[position])
            true
        }
    }

    override fun getItemCount() = hhList.size

}
