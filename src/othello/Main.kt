package othello

import othello.player.Human
import othello.player.Random
fun main(){
    val board = Board(8)
    //Humanは標準入力のプレイヤー
    //Randomはランダム設置のプレイヤー
    val player1 = Human(Stone.BLACK,"プレイヤー1")
    val player2 = Random(Stone.WHITE,"CPU")
    val game = Game(player1,player2,board)
    game.start()
}

