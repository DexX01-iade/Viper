package pt.iade.ei.gamestoreviper.models

import android.os.Parcelable
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class  GameItem(
    val id: Int,
    val nome: String,
    val descricao: String,
    val preco: String,
    val imageVector: @RawValue ImageVector

) : Parcelable