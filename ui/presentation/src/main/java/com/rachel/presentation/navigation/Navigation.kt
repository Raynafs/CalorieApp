package  com.rachel.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rachel.presentation.nutrients.NutrientsScreen
import com.rachel.presentation.calorie.CaloriesScreen


@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Calories.route) {
        composable(route = Screens.Calories.route) {
            CaloriesScreen(navController = navController)
        }
        composable(
            route = Screens.Nutrients.route.plus("/{id}"),
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            NutrientsScreen(navController = navController)
        }
    }

}