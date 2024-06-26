package android.luispouso.rickandmorty.presentation.ui.list.adapter

import android.luispouso.rickandmorty.common.extensions.loadImageWithGlide
import android.luispouso.rickandmorty.databinding.ItemCharacterBinding
import android.luispouso.rickandmorty.domain.model.CharacterModel
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter(private var characters: List<CharacterModel> = emptyList(), private val listener: (CharacterModel) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {


    fun updateCharacters(newCharacters: List<CharacterModel>) {
        characters = newCharacters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.populate(character = characters[position])
    }

    override fun getItemCount(): Int = characters.size


    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun populate(character: CharacterModel) {
            itemView.setOnClickListener { listener(character) }

            with(binding) {
                tvName.text = character.name
                tvOrigin.text = character.origin
                ivItem.loadImageWithGlide(Uri.parse(character.image))
            }
        }
    }
}