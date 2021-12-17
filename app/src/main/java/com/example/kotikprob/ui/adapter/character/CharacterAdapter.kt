package com.example.kotikprob.ui.adapter.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kotikprob.common.base.BaseComparator
import com.example.kotikprob.data.network.dtos.character.Character
import com.example.kotikprob.databinding.ItemCharacterBinding

class CharacterAdapter(
    private val onItemClick: (id: Int) -> Unit,
    private val onItemLongClickListener: (photo: String) -> Unit
) : PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(
    BaseComparator()
) {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class CharacterViewHolder(

        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition).apply {
                    onItemClick(this!!.id)
                }
            }
            itemView.setOnLongClickListener {
                getItem(absoluteAdapterPosition).apply {
                    onItemLongClickListener(this?.image.toString())
                }
                false
            }
        }

        fun onBind(item: Character) = with(binding) {
            imgCharacter.load(item.image)
            textNameCharacter.text = item.name
            textIdCharacter.text = item.id.toString()
            textStatus.text = item.status
        }
    }
}