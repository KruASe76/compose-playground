import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import javax.swing.filechooser.FileSystemView
import java.nio.file.Files
import java.nio.file.Paths
import me.kruase.db.*
import ui.common.MainTheme


val oneLineButtonHeight by lazy { 40.dp }


@Composable
fun App() {
    val dbPath = Paths.get(FileSystemView.getFileSystemView().defaultDirectory.path, "ComposePlayground")
    Files.createDirectories(dbPath)

    val driver = JdbcSqliteDriver("jdbc:sqlite:${Paths.get(dbPath.toString(), "base.db")}")
    Database.Schema.create(driver)
    val db = Database(driver)

    val hotelQueries = db.hotelQueries
    if (hotelQueries.selectAll().executeAsList().isEmpty()) {
        hotelQueries.insert(Hotel(
            name = "Интеграл",
            region_id = 1L,
            phone = "88005553535",
            manager_id = 2L,
            description = "Мотемотический отель"
        ))
    }

    var text by remember { mutableStateOf("Hello, World!") }
    var otherText = hotelQueries.selectAll().executeAsList()[0].run { "$name - $description" }

    MainTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
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
