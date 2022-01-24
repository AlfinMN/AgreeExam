package com.test.agreeexam.home.data

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.test.agreeexam.databinding.ItemTopRatingBinding

class TopRatingAdapter (val list : List<GamesModel>,var activity : Activity): RecyclerView.Adapter<TopRatingVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatingVH {
        val view = ItemTopRatingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TopRatingVH((view))
    }

    override fun onBindViewHolder(holder: TopRatingVH, position: Int) {
        holder.data(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class TopRatingVH (val binding : ItemTopRatingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun data(gamesModel: GamesModel){
            binding.apply {
                Glide.with(itemView)
                    .load(gamesModel.background_image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(imgGame)
                nameGame.text = gamesModel.name
                ratingstar.rating = gamesModel.rating
                rating.text = gamesModel.rating.toString()
            }
        }
}
