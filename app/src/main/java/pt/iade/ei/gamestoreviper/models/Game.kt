package pt.iade.ei.gamestoreviper.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val description: String,
    val companyName: String,
    val imageResId: Int,
    val items: List<GameItem>
) : Parcelable