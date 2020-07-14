package com.clean.app.views.feeds

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.clean.app.data.entity.Feeds
import com.clean.app.R
import com.clean.app.data.entity.Row
import com.clean.app.databinding.RowItemBinding

/**
 * Created by rohit.anvekar on 14/7/20.
 */
class FeedsAdapter(
    private val context: Context,
    private val row: MutableList<Row>,
    private val mListener: OnListItemInteractionListener?
) : RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder>() {

    interface OnListItemInteractionListener {
        fun onItemClicked(item: Feeds)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FeedsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_item,
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: FeedsAdapter.FeedsViewHolder, position: Int) {
        holder.rowItemBinding.row = row[position]
        holder.rowItemBinding.context = context
        holder.setIsRecyclable(false)

    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount() = row.size

    inner class FeedsViewHolder(val rowItemBinding: RowItemBinding) :
        RecyclerView.ViewHolder(rowItemBinding.root)


}
