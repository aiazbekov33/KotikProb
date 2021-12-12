package com.example.kotikprob.ui.adapter.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kotikprob.common.base.BaseComparator
import com.example.kotikprob.data.network.dtos.character.Character
import com.example.kotikprob.databinding.ItemCharacterBinding

class CharacterAdapter : PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(
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

        fun onBind(item: Character) = with(binding) {
            imgCharacter.load(item.image)
            textNameCharacter.text = item.name

        }
    }
}