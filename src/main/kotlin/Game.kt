class Game {
    private val board = MutableList<Cell>(size = 9) { Cell.Empty }
    private var status: Status = Status.Idle
    private lateinit var player: Player

    fun start() {
        status = Status.Running
        println(" ------------------------------ ")
        println("| Welcome to TIC-TAC-TOE Game! |")
        println("|    Pick a number from 0-8    |")
        println(" ------------------------------ ")
        getName()
    }

    private fun getName() {
        print("Choose Your Name: ")
        val name = readlnOrNull()
        try {
            require(value = name != null)
            player = Player(name = name, symbol = 'X')
            println("It's your move, $name")
            printBoard()
        }catch (e: Throwable) {
            println("Invalid Name.")
        }
    }

    private fun printBoard() {
        println()
        println(" ------- ")
        println("| ${board[0].placeHolder} ${board[1].placeHolder} ${board[2].placeHolder} |")
        println("| ${board[3].placeHolder} ${board[4].placeHolder} ${board[5].placeHolder} |")
        println("| ${board[6].placeHolder} ${board[7].placeHolder} ${board[8].placeHolder} |")
        println(" ------- ")
        println()
    }
}

data class Player(
    val name: String = "Computer",
    val symbol: Char = 'O'
)

sealed class Status {
    object Idle: Status()
    object Running: Status()
    object GameOver: Status()
}

sealed class Cell(val placeHolder: Char) {
    object Empty: Cell(placeHolder = '_')
    data class Filled(val player: Player): Cell(placeHolder = player.symbol)
}