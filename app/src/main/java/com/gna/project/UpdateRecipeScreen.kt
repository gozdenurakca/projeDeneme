package com.gna.project


import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class UpdateRecipeScreen(navController: NavHostController, recipeViewModel: RecipeViewModel, title: String) {
    val client = OkHttpClient()

    @OptIn(DelicateCoroutinesApi::class)
    fun modifyItem(
        title: String,
        ingredient: String?,
        recipe: String?,
        preparationTime: String?,
        calories: String?,
        imageName: String?
    ) {
        val baseUrl = "${base}/modify_data"
        val json = buildString {
            append("{")
            append("\"Title\":\"$title\"")
            ingredient?.let { append(", \"Ingredient\":\"$it\"") }
            recipe?.let { append(", \"Recipe\":\"$it\"") }
            preparationTime?.let { append(", \"PreparationTime\":\"$it\"") }
            calories?.let { append(", \"Calories\":\"$it\"") }
            imageName?.let { append(", \"ImageName\":\"$it\"") }
            append("}")
        }
        val requestBody = json.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url(baseUrl)
            .post(requestBody)
            .build()

        GlobalScope.launch(Dispatchers.IO) {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    // Handle error
                }
            }
        }
    }
}
