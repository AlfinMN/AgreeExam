package com.test.agreeexam.home.data

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.test.agreeexam.databinding.ItemLatestBinding

class LatestAdapter (val list : List<GamesModel>,var activity : Activity) :  RecyclerView.Adapter<LatesVH>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatesVH {
        val view = ItemLatestBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LatesVH((view))
    }

    override fun onBindViewHolder(holder: LatesVH, position: Int) {
        holder.data(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class LatesVH (val binding : ItemLatestBinding) : RecyclerView.ViewHolder(binding.root){
    fun data(gamesModel: GamesModel){
        binding.apply {
            Glide.with(itemView)
                .load(gamesModel.background_image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(imgLatest)
            titleGame.text = gamesModel.name
            no.text = (adapterPosition + 1).toString()

        }
    }
}
