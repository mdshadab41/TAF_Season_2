package com.example.taf_season_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class ListFragment: BaseMainActivityFragment(R.layout.fragment_list) {

    private lateinit var soccerTileAdapter: SoccerTileAdapter

    private val soccerTileList: ArrayList<SoccerTile>
        get() = MainActivity.soccerTileList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.apply {
            title = "EPL Home"
            setDisplayHomeAsUpEnabled(false)
        }

        soccerTileAdapter = SoccerTileAdapter(soccerTileList,MainActivity)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recylerView)
        recyclerView.adapter = soccerTileAdapter
    }

    override fun onResume() {
        super.onResume()
        soccerTileAdapter.notifyDataSetChanged()
    }

    fun onFavouriteClicked(position: Int){
        soccerTileAdapter.notifyItemChanged(position)
    }

}