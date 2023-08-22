package semir.mahovkic.mahalahub.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import semir.mahovkic.mahalahub.R

@Composable
fun LogoImage(
    modifier: Modifier,
    size: Dp = 150.dp
) {
    Image(
        painter = painterResource(
            R.mipmap.logo
        ) as Painter,
        contentDescription = "",
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(0.dp, Color.Transparent, CircleShape)
            .background(Color.Transparent)
    )
}