package com.example.kotikprob.presentation.ui.adapter.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotikprob.common.base.BaseComparator
import com.example.kotikprob.data.network.dtos.location.Location
import com.example.kotikprob.databinding.ItemLocationBinding

class LocationAdapter(
    private val onItemClick: (id: Int) -> Unit
) : PagingDataAdapter<Location, LocationAdapter.LocationViewHolder>(
    BaseComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class LocationViewHolder(
        private val binding: ItemLocationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(item: Location) = with(binding) {
            textNameLocation.text = item.name
            textTypeLocation.text = item.type
        }
    }
}

