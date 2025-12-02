package pt.iade.ei.gamestoreviper

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.gamestoreviper.controllers.GameController
import pt.iade.ei.gamestoreviper.models.Game
import pt.iade.ei.gamestoreviper.view.GameCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val games = GameController.getGames()

        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Color.Black,
                    surface = Color(0xFF1E1E1E),
                    primary = Color(0xFF8D6E63),
                    onBackground = Color.White,
                    onSurface = Color.White
                )
            ) {
                MainScreen(games = games) { selectedGame ->
                    val intent = Intent(this, GameDetailActivity::class.java).apply {
                        putExtra("GAME_DATA", selectedGame)
                    }
                    startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun MainScreen(games: List<Game>, onGameClick: (Game) -> Unit) {
    Scaffold(
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ggsv_lol),
                    contentDescription = "Logo GameStore",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(16.dp))


                Text(
                    text = "Game Store Viper",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            LazyColumn {
                items(games) { game ->
                    GameCard(game = game, onClick = { onGameClick(game) })
                }
            }
        }
    }
}