package com.dhgrupo5.popfilm.data.fixed

import com.dhgrupo5.popfilm.model.PlayList


class PlayListsData {

    companion object{

        fun getInitialPlayLists()
             //= mutableListOf<PlayList>()
            = mutableListOf(
                PlayList(
                    title = "Mech Files  " +
                        "Iron Man",
                    uid = "UCxwitsUVNzwS5XBSC5UQV8Q"
                ),
                PlayList(
                    title = "Virtual Launch Event " +
                        "The Falcon and The Winter Soldier",
                    uid = "cxifB--QfR4"
                ),
                PlayList(
                    title = "Cronológia dos filmes Marvel " +
                        "Canal Aficionados " ,
                    uid = "ZOkwhi9ytnw"
                ),
                PlayList(
                    title = "Próximos filmes da Marvel ",
                    uid = "mdSAEfzm6s8"
                ),
                PlayList(
                    title = "WandaVision Clip ",
                    uid = "ZAk32wCK18g"
                ),
                PlayList(
                    title = "Black Panthers " ,
                    uid = "W1waReygdb0"
                )
            )
    }
}