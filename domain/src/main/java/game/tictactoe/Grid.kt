@file:Suppress("PackageName")

package game.tictactoe

object Grid {

    const val ROUND_AVATAR = 0
    const val CROSS_AVATAR = 1

    val grid: Triple<
            Triple<Boolean?, Boolean?, Boolean?>,
            Triple<Boolean?, Boolean?, Boolean?>,
            Triple<Boolean?, Boolean?, Boolean?>
            > = Triple(
        Triple(null, null, null),
        Triple(null, null, null),
        Triple(null, null, null),
    )


    class Player(val avatar: Int? = null)

    @JvmStatic
    val players by lazy { Pair(Player(), Player()) }


}