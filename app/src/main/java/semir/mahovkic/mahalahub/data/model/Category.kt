package semir.mahovkic.mahalahub.data.model

data class Category(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val ordering: Int
)