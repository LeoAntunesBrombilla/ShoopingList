package com.example.shoopinglist.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoopinglist.data.Item
import com.example.shoopinglist.databinding.ItemListBinding

class ItemsAdapter(private val itemList: List<Item>) :
    RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private lateinit var binding: ItemListBinding

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Item) {
            binding.apply {
                itemName.text = item.name
                itemQuantity.text = item.quantity.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

}