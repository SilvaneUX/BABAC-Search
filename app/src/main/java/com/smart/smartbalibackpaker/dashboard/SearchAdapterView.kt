package com.smart.smartbalibackpaker.dashboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smart.smartbalibackpaker.DetailActivity
import com.smart.smartbalibackpaker.R
import com.smart.smartbalibackpaker.core.data.source.local.entity.TourismDataEntity
import com.smart.smartbalibackpaker.databinding.ItemDestinationGuideBinding
import com.smart.smartbalibackpaker.databinding.ItemSearchGuideViewBinding

class SearchAdapterView  : PagedListAdapter<TourismDataEntity, SearchAdapterView.ListViewHolder>(
    DIFF_CALLBACK){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemSearchGuideViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val search = getItem(position)
        Glide.with(holder.itemView.context)

            .load("https://balibackpacker.co.id/storage/public/pictures/thumbnail/${search?.thumbnail}")
            .placeholder(R.drawable.placeholder)
            .into(holder.binding.imgPlace)
        holder.binding.tvTitle.text = search?.title
        holder.binding.tvAddress.text = search?.address
        holder.itemView.setOnClickListener{


            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.apply {
                putExtra(DetailActivity.EXTRA_ID, search?.id)
                putExtra(DetailActivity.EXTRA_TYPE, search?.type)
            }
            it.context.startActivity(intent)
        }


    }

    class ListViewHolder (val binding : ItemSearchGuideViewBinding) :RecyclerView.ViewHolder(binding.root) {
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TourismDataEntity>() {
            override fun areItemsTheSame(
                oldItem: TourismDataEntity,
                newItem: TourismDataEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TourismDataEntity,
                newItem: TourismDataEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }



}