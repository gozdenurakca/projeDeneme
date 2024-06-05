package com.gna.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gna.project.ui.theme.ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                val navController = rememberNavController()
                val recipeViewModel: RecipeViewModel by viewModels()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("home") {
                        HomeScreen(navController = navController,
                            recipeViewModel = recipeViewModel)
                    }
                    composable("addRecipe") {
                        AddRecipeScreen(navController, recipeViewModel)
                    }

                    composable(
                        route = "RecipeDetailScreen/{title}/{ingredient}/{recipe}/{preparationTime}/{calories}/{imageName}",
                        arguments = listOf(
                            navArgument("title") { type = NavType.StringType },
                            navArgument("ingredient") { type = NavType.StringType },
                            navArgument("recipe") { type = NavType.StringType },
                            navArgument("preparationTime") {
                                type = NavType.StringType
                            },
                            navArgument("calories") { type = NavType.StringType },
                            navArgument("imageName") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        RecipeDetailScreen(
                            navController = navController,
                            title = backStackEntry.arguments?.getString("title") ?: "",
                            ingredient = backStackEntry.arguments?.getString("ingredient")
                                ?: "",
                            recipe = backStackEntry.arguments?.getString("recipe")
                                ?: "",
                            preparationTime = backStackEntry.arguments?.getString("preparationTime")
                                ?: "",
                            calories = backStackEntry.arguments?.getString("calories")
                                ?: "",
                            imageName = backStackEntry.arguments?.getString("imageName")
                                ?: "",
                            onDelete = {
                                // Tarifi silme işlemi
                                val title =
                                    backStackEntry.arguments?.getString("title") ?: ""
                                recipeViewModel.deleteRecipeById(id)
                                navController.popBackStack()
                            },
                            onUpdate = {
                                // Güncelleme ekranına geçiş
                                val title =
                                    backStackEntry.arguments?.getString("title") ?: ""
                                navController.navigate("updateRecipe/$title")
                            }
                        )
                    }
                    composable(
                        route = "updateRecipe/{title}",
                        arguments = listOf(
                            navArgument("title") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val title = backStackEntry.arguments?.getString("title") ?: ""
                        UpdateRecipeScreen(navController, recipeViewModel, title)
                    }
                }
            }
        }
    }
}