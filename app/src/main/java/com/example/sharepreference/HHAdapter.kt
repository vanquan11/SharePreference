package com.example.sharepreference

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class HHAdapter(private val hhList: List<Result>) : RecyclerView.Adapter<HHAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewId: TextView
        val textViewName: TextView
        val textViewPrice: TextView
        val textViewPriceLarg: TextView
        val textViewCreateDate: TextView
        val textViewModifDate: TextView
        val textViewMaxQuan: TextView
        val textViewUnit: TextView

        init {
            textViewId = view.findViewById(R.id.txtId)
            textViewName = view.findViewById(R.id.txtName)
            textViewPrice = view.findViewById(R.id.txtPrice)
            textViewPriceLarg = view.findViewById(R.id.txtPriceLarg)
            textViewCreateDate = view.findViewById(R.id.txtcreateDate)
            textViewModifDate = view.findViewById(R.id.txtModifDate)
            textViewMaxQuan = view.findViewById(R.id.txtMaxQuan)
            textViewUnit = view.findViewById(R.id.txtUnit)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HHAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.itemhh, viewGroup, false)

        return HHAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: HHAdapter.ViewHolder, position: Int) {

        viewHolder.textViewId.text = hhList[position].id.toString()
        viewHolder.textViewName.text = hhList[position].name
        viewHolder.textViewPrice.text = hhList[position].price.toString()
        viewHolder.textViewPriceLarg.text = hhList[position].priceLargeUnit.toString()
        viewHolder.textViewCreateDate.text = hhList[position].createdDate
        viewHolder.textViewModifDate.text = hhList[position].modifiedDate
        viewHolder.textViewMaxQuan.text = hhList[position].maxQuantity.toString()
        viewHolder.textViewUnit.text = hhList[position].unit
    }

    override fun getItemCount() = hhList.size
}
