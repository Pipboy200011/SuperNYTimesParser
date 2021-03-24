package com.n.supernytimesparser.main.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.n.supernytimesparser.R
import com.n.supernytimesparser.base.presentation.listener.IAdapterItemClickListener
import com.n.supernytimesparser.databinding.NewswireElementBinding
import com.n.supernytimesparser.main.models.domain.NewswireBean

/**
 * ViewHolder for news list
 *
 * @author Pavel Apanovskii on 24/03/2021.
 */
class NewswireElementViewHolder(
    private val binding: NewswireElementBinding,
    private val clickListener: IAdapterItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(newswireBean: NewswireBean) {
        setData(newswireBean)
    }

    private fun setData(newswireBean: NewswireBean) {
        binding.container.setOnClickListener { clickListener.onClick(adapterPosition) }

        binding.title.text = newswireBean.title
        Glide
            .with(binding.thumbnail)
            .load(newswireBean.thumbnail)
            .placeholder(R.drawable.ic_logo)
            .error(R.drawable.ic_logo)
            .centerInside()
            .into(binding.thumbnail)
    }
}