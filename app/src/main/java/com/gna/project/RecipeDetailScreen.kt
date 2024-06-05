package com.gna.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeDetailScreen(
    navController: NavController,
    title: String,
    ingredient: String,
    recipe: String,
    preparationTime: String,
    calories: String,
    imageName: String,
    onDelete: () -> Unit,
    onUpdate: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "Back")
                }
                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = onDelete) {
                    Text(text = "Delete")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = onUpdate) {
                    Text(text = "Update")
                }
            }

            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            val painter = rememberAsyncImagePainter(imageName)
            Image(
                painter = painter,
                contentDescription = imageName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ingredients",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = ingredient)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Steps",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = recipe)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Preparation Time: $preparationTime",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Calories: $calories",
                fontSize = 16.sp
            )
        }
    }
}