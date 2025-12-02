package pt.iade.ei.gamestoreviper.controllers

import pt.iade.ei.gamestoreviper.models.Game
import pt.iade.ei.gamestoreviper.models.GameItem
import pt.iade.ei.gamestoreviper.R

object GameController {

    fun getGames(): List<Game> {
        return listOf(
            Game(
                id = 1,
                name = "League of Legends",
                description = "Um MOBA estratégico onde duas equipas de cinco Jogadores cada um com um poderoso campeão, enfrentam-se assim para destruir o Nexus da base inimiga.",
                companyName = "Riot Games",
                imageResId = R.drawable.lol_cover,
                items = listOf(
                    GameItem(
                        id = 101,
                        name = "Skin Kaisa KDA",
                        description = "Uma skin Lendária, Escolhida por 'Ruler' AdCarry da GEN.G para o Worlds de 2023",
                        price = 25.99,
                        imageResId = R.drawable.kaisa_kda,
                    ),
                    GameItem(
                        id = 102,
                        name = "Baú Hextech",
                        description = "Contém espólios aleatórios, como fragmentos de skin, campeões ou gemas raras.",
                        price = 1.99,
                        imageResId = R.drawable.bau_lol,
                    ),
                    GameItem(
                        id = 103,
                        name = "Passe de Batalha",
                        description = "Desbloqueia missões exclusivas e recompensas premium durante o evento atual.",
                        price = 12.50,
                        imageResId = R.drawable.battle_pass_lol,
                    )
                )
            ),
            Game(
                id = 2,
                name = "Brawl Stars",
                description = "Batalhas frenéticas 3v3 e battle royale feitas para telemóvel. Joga com amigos ou sozinho em vários modos.",
                companyName = "Supercell",
                imageResId = R.drawable.brawl_cover,
                items = listOf(
                    GameItem(
                        id = 201,
                        name = "Passe Brawl",
                        description = "Ganha recompensas extra, incluindo um Brawler Épico a escolha e skins exclusivas da temporada.",
                        price = 9.99,
                        imageResId = R.drawable.battle_pass_lol
                    ),
                    GameItem(
                        id = 202,
                        name = "Pacote de Gemas",
                        description = "170 gemas para comprar skins, caixas ou Ouro na loja.",
                        price = 10.99,
                        imageResId = R.drawable.gemas_brawl,
                    ),
                    GameItem(
                        id = 203,
                        name = "Skin Corvo Fénix",
                        description = "O Crow renasce das cinzas com esta skin lendária e efeitos de fogo incríveis.",
                        price = 19.99,
                        imageResId = R.drawable.corvo_skin,
                    )
                )
            )
        )
    }
}