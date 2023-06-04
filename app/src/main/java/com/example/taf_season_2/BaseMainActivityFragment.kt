package com.example.taf_season_2

import androidx.fragment.app.Fragment

abstract class BaseMainActivityFragment(layoutId: Int) : Fragment(layoutId) {

    val mainActivity: MainActivity by lazy { activity as MainActivity }
}
