import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codingchallenge.pixabayapi.Screen
import com.codingchallenge.pixabayapi.ui.components.MainContent
import com.codingchallenge.pixabayapi.ui.components.MainContentItem

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainContent.route)
    {
        composable(route = Screen.MainContent.route) {


        }
        composable(route = Screen.DetailScreen.route, arguments = listOf())
        {

        }


    }


    @Composable
    fun DetailScreen(name: String?) {


    }


}
