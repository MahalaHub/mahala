package semir.mahovkic.mahalahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import semir.mahovkic.mahalahub.ui.MainScreen
import semir.mahovkic.mahalahub.ui.theme.MahalaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MahalaTheme {
                MainScreen()
            }
        }
    }
}
