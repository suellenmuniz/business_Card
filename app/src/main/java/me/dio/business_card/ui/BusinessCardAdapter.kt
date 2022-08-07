package me.dio.business_card.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.dio.business_card.data.Card
import me.dio.business_card.databinding.ItemBusinessCardBinding

class BusinessCardAdapter :
    ListAdapter<Card, BusinessCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}
    var listenerDelete: (Card) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemBusinessCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Card) {
            binding.tvNome.text = item.nome
            binding.tvTelefone.text = item.telefone
            binding.tvEmail.text = item.email
            binding.tvEmpresa.text = item.empresa
            binding.cvItem.setBackgroundColor(Color.parseColor(item.button))
            binding.cvItem.setOnClickListener {

                val builder = AlertDialog.Builder(it.context)
                builder.setTitle("Share or Delete")
                builder.setItems(arrayOf("Share", "Delete")) { _, which ->
                    when (which) {
                        0 -> listenerShare(it)
                        1 -> listenerDelete(item)
                    }
                }
                builder.show()

            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Card, newItem: Card) =
            oldItem.id == newItem.id

    }
}


