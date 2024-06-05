package com.gna.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


data class Recipe(
    val id: String,
    val title: String,
    val time: String,
    val imageRes: Int,
    val ingredients: List<Ingredient> = emptyList(),
    val steps: List<String> = emptyList(),
    val calories: String
)

val _recipes = MutableLiveData<List<Recipe>>()

data class Ingredient(
    val name: String,
    val amount: String
)

class RecipeViewModel : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> get() = _recipes


    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
        }
    }
    /*
    fun getRecipeById(recipeId: Int): StateFlow<Recipe?> {
        return MutableStateFlow(_recipes.value.find { it.id == recipeId })
    }



    fun updateRecipeTitle(recipeId: Int, newTitle: String) {

    }

    fun updateRecipeTime(recipeId: Int, newTime: String) {

    }

 */


    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            _recipes.value += recipe
        }
    }

    /*
    fun deleteRecipe(recipe: Int) {
        viewModelScope.launch {
            // Delete the recipe from the repository
            _recipes.value = _recipes.value.filter { it.id != recipe.id }
        }
    }
    */


    fun deleteRecipeById(id: Int) {
        _recipes.value = _recipes.value?.filter { it.id.equals(id)}!!
    }

}
