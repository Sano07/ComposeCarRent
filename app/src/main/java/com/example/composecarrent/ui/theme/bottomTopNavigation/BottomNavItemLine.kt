package com.example.composecarrent.ui.theme.bottomTopNavigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BottomNavItemLine(navController : NavController) {
    // список іtem-ов которые будут отображаться внизу
    val listItems = listOf(BottomNavItems.Home, BottomNavItems.Favorite, BottomNavItems.Settings)

    val selectedItem = remember { mutableStateOf("Home") }

    NavigationBar {
        // перебор списка іtem-ов для отображения в нав баре
        listItems.forEach { item ->
            NavigationBarItem(
                selected = selectedItem.value == item.title,
                onClick = {
                    selectedItem.value = item.title
                    navController.navigate(item.route) {
                        // чтобы не дублировать экраны в backstack
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(painter = painterResource(id = item.iconId), contentDescription = "Icon Logo")
                },
                label = {
                    Text(text = item.title, fontSize = 15.sp)
                },
                colors = NavigationBarItemDefaults.colors(),
            )
        }
    }
}