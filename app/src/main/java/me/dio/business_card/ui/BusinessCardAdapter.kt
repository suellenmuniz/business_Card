package me.dio.business_card.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.dio.business_card.data.BusinessCard
import me.dio.business_card.databinding.ItemBusinessCardBinding

class BusinessCardAdapter :
    ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}
    var listenerDelete: (BusinessCard) -> Unit = {}


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


        fun bind(item: BusinessCard) {
            binding.tvNome.text = item.nome
            binding.tvTelefone.text = item.telefone
            binding.tvEmail.text = item.email
            binding.tvNomeEmpresa.text = item.empresa
            binding.cvContentCard.setBackgroundColor(Color.parseColor(item.fundoPersonalizado))
            binding.cvContentCard.setOnClickListener {

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


    class DiffCallback : DiffUtil.ItemCallback<BusinessCard>() {
        override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
            oldItem.id == newItem.id

    }
}


