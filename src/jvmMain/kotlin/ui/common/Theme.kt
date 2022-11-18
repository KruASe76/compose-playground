package ui.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable


private val darkPalette by lazy { darkColors(

) }

private val typography by lazy { Typography() }

private val shapes by lazy { Shapes() }


@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
//        colors = if (darkTheme) darkPalette else lightPalette,
        colors = darkPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

