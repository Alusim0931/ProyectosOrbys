import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.Brightness5
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebamoviedb.R
import kotlinx.coroutines.launch
import com.example.pruebamoviedb.ui.screens.MovieList
import com.example.pruebamoviedb.ui.viewmodels.DataMovies


@Composable
fun MyApp() {
    val isDarkTheme = remember { mutableStateOf(false) }
    MyAppWithDrawer(isDarkTheme)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppWithDrawer(isDarkTheme: MutableState<Boolean>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                scope.launch {
                    if (drawerState.isOpen) {
                        drawerState.close()
                    } else {
                        drawerState.open()
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color.Blue
                )
            }
            ThemeToggleButton(isDarkTheme) { isDarkTheme.value = it }
        }

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Configuración", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(24.dp))
                }
            },
            content = {
                val movies = listOf(
                    DataMovies("Oppenhaimer", R.drawable.oppenhaimer),
                    DataMovies("Dune", R.drawable.dune),
                    DataMovies("Kung Fu Panda 4", R.drawable.panda4)
                )

                MovieList(movies = movies) {}
            }
        )
    }
}

@Composable
fun ThemeToggleButton(isDarkTheme: MutableState<Boolean>, onToggle: (Boolean) -> Unit) {
    IconButton(
        onClick = { onToggle(!isDarkTheme.value) }
    ) {
        Icon(
            imageVector = if (isDarkTheme.value) Icons.Filled.Brightness5 else Icons.Filled.Brightness2,
            contentDescription = if (isDarkTheme.value) "Cambiar a modo claro" else "Cambiar a modo oscuro"
        )
    }
}