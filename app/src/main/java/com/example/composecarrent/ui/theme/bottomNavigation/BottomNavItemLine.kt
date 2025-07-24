package com.example.composecarrent.ui.theme.bottomNavigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavItemLine(navController: NavController) {
    // список іtem-ов которые будут отображаться внизу
    val listItems = listOf(BottomNavItems.Home, BottomNavItems.Favorite, BottomNavItems.Settings)
    // отслеживание текущего активного іtem-а
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar {
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(painter = painterResource(id = item.iconId), contentDescription = "Icon Logo")
                },
                label = {
                    Text(text = item.title, fontSize = 9.sp)
                },
                colors = NavigationBarItemDefaults.colors(),
            )
        }
    }
}