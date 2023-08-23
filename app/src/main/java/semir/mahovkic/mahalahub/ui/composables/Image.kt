package semir.mahovkic.mahalahub.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import semir.mahovkic.mahalahub.R


@Composable
fun LogoImage(
    modifier: Modifier = Modifier,
    size: Dp = 150.dp
) {
    CircularImage(modifier = modifier, size = size)
}

@Composable
fun CardImage(
    modifier: Modifier = Modifier,
    image: Int = R.mipmap.logo,
    size: Dp = 60.dp
) {
    CircularImage(modifier = modifier, icon = image, size = size)
}

@Composable
fun IconImage(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp
) {
    Icon(imageVector = icon, modifier = modifier, contentDescription = "")
}

@Composable
private fun CircularImage(
    modifier: Modifier = Modifier,
    icon: Int = R.mipmap.logo,
    size: Dp = 150.dp
) {
    Image(
        painter = painterResource(icon),
        contentDescription = "",
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(0.dp, Color.Transparent, CircleShape)
            .background(Color.Transparent)
    )
}
