package pt.iade.ei.gamestoreviper

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import pt.iade.ei.gamestoreviper.models.Game
import pt.iade.ei.gamestoreviper.models.GameItem
import pt.iade.ei.gamestoreviper.view.GameItemRow
import pt.iade.ei.gamestoreviper.view.ItemBottomSheetContent

class GameDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        val game = intent.getParcelableExtra<Game>("GAME_DATA")

        setContent {
            MaterialTheme {
                if (game != null) {
                    GameDetailScreen(
                        game = game,
                        onBackClick = { finish() },
                        onBuyItem = { item ->
                            Toast.makeText(
                                this,
                                "Acabou de comprar o item ${item.name} por $${item.price}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                } else {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Erro ao carregar os dados necessários do jogo.")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(game: Game, onBackClick: () -> Unit, onBuyItem: (GameItem) -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf<GameItem?>(null) }
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(game.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Row(modifier = Modifier.padding(vertical = 16.dp)) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFF000000)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = game.imageResId),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = game.description,
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        lineHeight = 20.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
                Text(
                    text = "Purchasable Items",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            items(game.items) { item ->
                GameItemRow(item = item, onClick = {
                    selectedItem = item
                    showBottomSheet = true
                })
            }
        }
        if (showBottomSheet && selectedItem != null) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                ItemBottomSheetContent(
                    item = selectedItem!!,
                    onBuyClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                                onBuyItem(selectedItem!!)
                            }
                        }
                    }
                )
            }
        }
    }
}