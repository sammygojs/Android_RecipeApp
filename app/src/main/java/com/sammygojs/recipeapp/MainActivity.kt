package com.sammygojs.recipeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sammygojs.recipeapp.ui.theme.RecipeAppTheme
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

data class Recipe(
    val name: String,
    val time: String,
    val description: String,
    val instructions: String
)

val recipes = listOf(
    Recipe(
        name = "Pasta Carbonara",
        time = "30 minutes",
        description = "A classic Italian pasta dish made with eggs, cheese, bacon, and pepper.",
        instructions = "1. Cook pasta according to package instructions.\n" +
                "2. In a skillet, cook bacon until crispy. Remove from skillet and drain on paper towels.\n" +
                "3. In a bowl, whisk together eggs, grated cheese, and black pepper.\n" +
                "4. Drain cooked pasta and add it to the skillet with the bacon grease.\n" +
                "5. Remove skillet from heat and quickly stir in the egg mixture.\n" +
                "6. The heat from the pasta will cook the eggs and create a creamy sauce.\n" +
                "7. Serve immediately, topped with additional cheese and black pepper."
    ),
    Recipe(
        name = "Chicken Stir Fry",
        time = "25 minutes",
        description = "A quick and easy stir-fry recipe with tender chicken and fresh vegetables.",
        instructions = "1. In a wok or skillet, heat oil over medium-high heat.\n" +
                "2. Add sliced chicken and stir-fry until cooked through.\n" +
                "3. Remove chicken from skillet and set aside.\n" +
                "4. Add chopped vegetables (such as bell peppers, broccoli, and carrots) to the skillet.\n" +
                "5. Stir-fry until vegetables are tender-crisp.\n" +
                "6. Return cooked chicken to the skillet.\n" +
                "7. Add soy sauce and any desired seasonings.\n" +
                "8. Cook for an additional 2-3 minutes, stirring constantly.\n" +
                "9. Serve hot, over rice if desired."
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            RecipeAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
            ListApp()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "recipeList") {
        composable("recipeList") {
            RecipeListScreen(navController)
        }
        composable("recipeDetail") {
            RecipeDetailScreen(navController)
        }
    }
}

@Composable
fun RecipeListScreen(navController: NavHostController) {
    @Composable
    fun RecipeList(recipes: List<Recipe>, onItemClick: (Recipe) -> Unit) {
        LazyColumn {
            items(recipes) { recipe ->
                RecipeItem(recipe = recipe, onItemClick = onItemClick)
            }
        }
    }
    // Your RecipeList composable here
}


@Composable
fun RecipeItem(recipe: Recipe, onItemClick: (Recipe) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(recipe) },
//        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = recipe.name, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Time: ${recipe.time}", color = Color.Gray)
        }
    }
}


@Composable
fun RecipeDetailScreen(navController: NavHostController) {
    @Composable
    fun RecipeDetail(recipe: Recipe) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), // Adjust the top padding to accommodate the top app bar
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = recipe.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Time: ${recipe.time}",
                fontSize = 18.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = recipe.description,
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Instructions:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = recipe.instructions,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        }
    }
    Button(
        onClick = { navController.navigate("recipeList") }
    ) {
        Text("Go back to recipe list")
    }

}