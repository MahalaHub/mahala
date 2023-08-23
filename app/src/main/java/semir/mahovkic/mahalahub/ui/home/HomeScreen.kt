package semir.mahovkic.mahalahub.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import semir.mahovkic.mahalahub.R
import semir.mahovkic.mahalahub.data.model.Category
import semir.mahovkic.mahalahub.ui.composables.CardImage

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
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(categories()) {
            CategoryCard(it, navigateToCategory)
        }
    }
}

@Composable
fun CategoryCard(category: Category, navigateToCategory: (categoryId: Int) -> Unit) {
    Surface(
        color = semir.mahovkic.mahalahub.ui.theme.CategoryCard,
        shape = MaterialTheme.shapes.large,
        shadowElevation = 2.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .height(150.dp)
            .clickable { navigateToCategory(category.id) }
    ) {
        Box {
            Text(
                text = category.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopCenter),
            )

            CardImage(Modifier.align(Alignment.Center), category.image, 80.dp)

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
            ) {
                Text(
                    text = category.description,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Center),
                )
            }
        }
    }
}

fun categories(): List<Category> {
    return listOf(
        Category(1, "Prevoz", "Prevoz putnika i robe", R.mipmap.travel, 1),
        Category(2, "Provod", "Pronađi lokaciju za izlazak", R.mipmap.hiking, 2)
    )
}