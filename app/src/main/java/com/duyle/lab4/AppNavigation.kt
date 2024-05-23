package com.duyle.lab4

enum class Screen {
    HOME,
    LOGIN,
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
}