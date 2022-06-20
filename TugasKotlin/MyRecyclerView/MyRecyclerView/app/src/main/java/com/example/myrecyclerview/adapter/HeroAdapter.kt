package com.example.myrecyclerview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myrecyclerview.data.models.HeroData
import com.example.myrecyclerview.databinding.ItemHeroBinding
import com.example.myrecyclerview.loadImageFromUrl

class HeroAdapter:RecyclerView.Adapter<HeroAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? =null
    private val listHero= ArrayList<HeroData>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(private val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(hero: HeroData) {
            with(binding) {
                imgItemPhoto.loadImageFromUrl(hero.avatar)
                tvItemName.text = hero.login
            }
            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(hero)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

    override fun getItemCount(): Int = listHero.size

    interface OnItemClickCallback {
        fun onItemClicked(data: HeroData)
    }
    fun setData(items: ArrayList<HeroData>) {
        listHero.clear()
        listHero.addAll(items)
        notifyDataSetChanged()
    }
}




