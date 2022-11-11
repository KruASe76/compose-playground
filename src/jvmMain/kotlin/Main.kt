// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


val oneLineButtonHeight by lazy { 34.dp }


@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    var otherText = "Compose Desktop is wonderful!"

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.TopCenter
        ) {
            Button(
                onClick = {
                    text = otherText.also { otherText = text }
                },
                modifier = Modifier
                    .heightIn(max = oneLineButtonHeight)
            ) {
                Text(text)
            }
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "How did you get here?",
        icon = painterResource("window_icon.svg")
    ) {
        App()
    }
}
