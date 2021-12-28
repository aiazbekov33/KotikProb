package com.example.kotikprob.presentation.ui.adapter.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotikprob.common.base.BaseComparator
import com.example.kotikprob.data.network.dtos.episode.Episode
import com.example.kotikprob.databinding.ItemEpisodeBinding

class EpisodeAdapter(
    private val onItemClick: (id: Int) -> Unit
) : PagingDataAdapter<Episode, EpisodeAdapter.EpisodeViewHolder>(
    BaseComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class EpisodeViewHolder(
        private val binding: ItemEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(item: Episode) = with(binding) {
            textNameEpisode.text = item.name
            textTypeEpisode.text = item.air_date
        }
    }
}

