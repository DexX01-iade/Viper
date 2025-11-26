package pt.iade.ei.gamestoreviper.models

import android.os.Parcelable
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.parcelize.Parcelize
import  kotlinx.parcelize.RawValue

@Parcelize
data class Game(
    val id: Int,
    val nome: String,
    val descrico: String,
    val NomeEmpresa: String,
    val imageVector: @RawValue ImageVector,
    val item: List<GameItem>
) : Parcelable