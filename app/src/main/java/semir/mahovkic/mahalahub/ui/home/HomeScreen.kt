package semir.mahovkic.mahalahub.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import semir.mahovkic.mahalahub.data.model.Category
import semir.mahovkic.mahalahub.ui.theme.CategoryCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToCategory: (categoryId: Int) -> Unit,
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    CategoriesList(navigateToCategory)
}

@Composable
fun CategoriesList(navigateToCategory: (categoryId: Int) -> Unit) {
    LazyColumn {
        items(categories(), key = { it.id }) {
            CategoryCard(it, navigateToCategory)
        }
    }
}

@Composable
fun CategoryCard(category: Category, navigateToCategory: (categoryId: Int) -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = CategoryCard,
        ),
        onClick = { navigateToCategory(category.id) }) {
        Text(text = category.name)
    }
}

fun categories(): List<Category> {
    return listOf(
        Category(1, "Prevoz", "Prevoz putnika i robe", "", 1),
        Category(2, "Provod", "Pronađi lokaciju za izlazak", "", 2)
    )
}