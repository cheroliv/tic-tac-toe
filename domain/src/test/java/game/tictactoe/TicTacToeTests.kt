package game.tictactoe

import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class TicTacToeTests {

    @Before
    fun init() = println("Before")

    @After
    fun tearDown() = println("After")


    @Test
    fun test1() = println("test 1")

    @Test
    fun test2() = println("test 2")

    @Test
    fun test3() = println("test 3")

    companion object {
        @JvmStatic
        @BeforeClass
        fun setUp() = println("Before class")

        @JvmStatic
        @AfterClass
        fun clean() = println("After class")
    }
}