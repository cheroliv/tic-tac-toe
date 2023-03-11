@file:Suppress(
    "NonAsciiCharacters",
    "TestFunctionName",
    "SpellCheckingInspection"
)

package game.ceelo

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import game.ceelo.Constant.CEELO_DICE_THROW_SIZE
import game.ceelo.Constant.ONE
import game.ceelo.Constant.SIX
import game.ceelo.Game.runDices
import game.ceelo.Playground.launchLocalGame
import game.ceelo.R.id.player_one_first_dice
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule.Companion.create
import org.mockito.Mockito.mock
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


@RunWith(AndroidJUnit4::class)
class GameServiceInstrumentedTest : KoinTest {

    @get:Rule
    val mockProvider by lazy { create { clazz -> mock(clazz.java) } }

    private val gameService: GameService by inject()

    @BeforeTest
    fun initService() { }

//    @org.junit.Ignore("TODO: too long! #DaftPunk")
    @Test
    fun ui_tests() {
        launch(GameActivity::class.java)
        androidx.test.espresso.Espresso.onView(withId(player_one_first_dice))
//            .check(androidx.test.espresso.assertion.ViewAssertions.matches(isCompletelyDisplayed()))
//            .check(androidx.test.espresso.assertion.ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun localDicesThrow_retourne_un_jeux_de_jet_de_dès_correct() {
        launchLocalGame().run {
            assertEquals(2, size)
            first().run hand@{
                assertEquals(CEELO_DICE_THROW_SIZE, this@hand.size)
                forEach { assert(it in ONE..SIX) }
            }
            last().run hand@{
                assertEquals(CEELO_DICE_THROW_SIZE, this@hand.size)
                forEach { assert(it in ONE..SIX) }
            }
        }
    }

    @Test
    fun allGames_retourne_toutes_les_parties_et_sont_correct() {
        gameService
            .allGames()
            .forEach { game ->
                assertEquals(2, game.size)
                game.first().run {
                    assertEquals(CEELO_DICE_THROW_SIZE, size)
                    forEach { assert(it in (ONE..SIX)) }
                }
                game.last().run {
                    assertEquals(CEELO_DICE_THROW_SIZE, size)
                    forEach { assert(it in (ONE..SIX)) }
                }
            }
    }

    @Test
    fun saveGame_ajoute_une_partie() {
        gameService.allGames().size.run {
            gameService.saveGame(listOf(runDices(), runDices()))
            assertEquals(this + 1, gameService.allGames().size)
        }
    }
}