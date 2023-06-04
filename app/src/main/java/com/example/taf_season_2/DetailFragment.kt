package com.example.taf_season_2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class DetailFragment: BaseMainActivityFragment(R.layout.fragment_detail) {
    private val soccerTile: SoccerTile by lazy {
        (activity as MainActivity).soccerTileList.find {
            it.id == requireArguments().getString("soccerTileId")
        } ?: SoccerTile()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        (activity as MainActivity).supportActionBar?.apply {
            title = "Club OverView"
            setDisplayHomeAsUpEnabled(true)
        }

        val headerImageView: ImageView = view.findViewById(R.id.teamHeaderImageView)
        val titleTextView: TextView = view.findViewById(R.id.tvTitle)
        val descriptionTextView: TextView = view.findViewById(R.id.tvDescription)
        val descriptionLongTextView: TextView = view.findViewById(R.id.tvLongDescription)

        //headerImageView.setImageResource(soccerTile.headerImageResId)
        titleTextView.text = soccerTile.title
        descriptionTextView.text = soccerTile.description
        descriptionLongTextView.text = soccerTile.descriptionLong

        Picasso.get().isLoggingEnabled = true
        Picasso.get()
            .load(soccerTile.headerImageUrl)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(headerImageView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_soccer_tile_detail, menu)
        if (soccerTile.isFavourite) {
            menu.findItem(R.id.menu_item_favourite)?.setIcon(R.drawable.ic_favourite_24dp)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                (activity as MainActivity).supportFragmentManager.popBackStack()
                true
            }

            R.id.menu_item_link -> {
                // Log.i("URL", soccerTile.teamUrl)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(soccerTile.teamUrl))
                startActivity(intent)
                true
            }

            R.id.menu_item_favourite -> {
                val isCurrentlyFavourited = soccerTile.isFavourite
                if (isCurrentlyFavourited) {
                    item.setIcon(R.drawable.ic_favourite_outline_24dp)
                } else {
                    item.setIcon(R.drawable.ic_favourite_24dp)
                }
                soccerTile.isFavourite = !isCurrentlyFavourited
                SharedPrefUtil.setSoccerTileFavourite(soccerTile.id, soccerTile.isFavourite)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
