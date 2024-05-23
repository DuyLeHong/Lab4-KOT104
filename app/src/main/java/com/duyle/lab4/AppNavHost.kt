package com.duyle.lab4

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Login.route) {
            LoginScreen(null, navController)
        }
        composable(
            "${NavigationItem.Home.route}/{userName}/{pass}",
            arguments = listOf(
                navArgument("userName") { type = NavType.StringType },
                navArgument("pass") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Retrieve the userName parameter from the arguments
            val userName = backStackEntry.arguments?.getString("userName")
            // Retrieve the userAge parameter from the arguments
            val pass = backStackEntry.arguments?.getString("pass")

            // Call the appropriate ScreenB content here, passing the userName and userAge
            LayoutPage2(NhanVienModel(userName!!, pass!!))
        }

    }

}