package com.n.supernytimesparser.main.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n.supernytimesparser.base.presentation.listener.IAdapterItemClickListener
import com.n.supernytimesparser.databinding.NewswireElementBinding
import com.n.supernytimesparser.main.models.domain.NewswireBean
import com.n.supernytimesparser.main.presentation.viewholder.NewswireElementViewHolder

/**
 * Adapter for news list
 *
 * @author Pavel Apanovskii on 24/03/2021.
 */
class NewswireElementsAdapter(private val clickListener: IAdapterItemClickListener) :
    RecyclerView.Adapter<NewswireElementViewHolder>() {

    private var list: ArrayList<NewswireBean> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewswireElementViewHolder {
        val binding = NewswireElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewswireElementViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: NewswireElementViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<NewswireBean>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}