package com.example.composecarrent.ui.theme.bottomNavigation

import com.example.composecarrent.R

sealed class BottomNavItems(val title: String, val iconId : Int, val route : String ) {
    object Home : BottomNavItems("Home", R.drawable.ic_home_item, "home_screen" )
    object Favorite : BottomNavItems("Favorite", R.drawable.ic_favorite_item, "favorite_screen" )
    object Settings : BottomNavItems("Settings", R.drawable.ic_settings_item, "settings_screen" )
}