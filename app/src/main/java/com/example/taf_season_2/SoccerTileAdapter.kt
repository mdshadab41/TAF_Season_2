package com.example.taf_season_2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class SoccerTileAdapter(
    private val data: ArrayList<SoccerTile>,
    private val soccerTileInterface: SoccerTileInterface
    ): RecyclerView.Adapter<SoccerTileAdapter.SoccerTileViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoccerTileViewHolder {
        return SoccerTileViewHolder(parent)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: SoccerTileViewHolder, position: Int) {
        holder.onBind(data[position], soccerTileInterface)
    }


    inner class SoccerTileViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_soccer_tile, parent, false)
    ){
        private val headerImageView: ImageView = itemView.findViewById(R.id.teamHeaderImageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.tvTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tvDescription)
        private val button: MaterialButton = itemView.findViewById(R.id.button)
        private val favouriteImage: AppCompatImageView = itemView.findViewById(R.id.ImgFavourite)

        fun onBind(soccerTile: SoccerTile, soccerTileInterface: SoccerTileInterface){
            headerImageView.setImageResource(soccerTile.headerImageResId)
            titleTextView.text = soccerTile.title
            descriptionTextView.text = soccerTile.description
            button.setOnClickListener {
                //Toast.makeText(it.context, "$adapterPosition clicked", Toast.LENGTH_SHORT).show()
                soccerTileInterface.onLearnMoreButtonClicked(adapterPosition)
            }
             val icon = if(soccerTile.isFavourite) R.drawable.ic_favourite_24dp else R.drawable.ic_favourite_outline_24dp
            favouriteImage.setImageResource(icon)
            favouriteImage.setOnClickListener {
                soccerTileInterface.onFavoriteClicked(adapterPosition)

            }

        }

    }
}
