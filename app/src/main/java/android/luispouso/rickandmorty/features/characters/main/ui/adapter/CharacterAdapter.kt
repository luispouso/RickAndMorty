package android.luispouso.rickandmorty.features.characters.main.ui.adapter

import android.luispouso.rickandmorty.core.ui.loadImageWithGlide
import android.luispouso.rickandmorty.databinding.ItemCharacterBinding
import android.luispouso.rickandmorty.features.characters.main.domain.model.Characters
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter(private var characters: List<Characters> = emptyList(), private val listener: (Characters) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {


    fun updateCharacters(newCharacters: List<Characters>) {
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

        fun populate(character: Characters) {
            itemView.setOnClickListener { listener(character) }

            with(binding) {
                tvName.text = character.name
                tvOrigin.text = character.origin
                ivItem.loadImageWithGlide(Uri.parse(character.image))
            }
        }
    }
}