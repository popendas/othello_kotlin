package othello

import othello.player.Player

class Game(private val player1: Player, private val player2: Player, private val board: Board) {
    /**
     * プロンプトでオセロを開始します
     *
     */
    fun start(){
        while(!this.board.isEnd){
            turn(player1)
            if (this.board.isEnd){
                break
            }
            turn(player2)
        }

        println(board)
        println("終了")
        val stoneMap = this.board.getStoneMap()
        for((stone,num) in stoneMap){
            print("${stone}:${num}個 ")
        }

    }

    private fun turn(player: Player){
        println(board)
        println("${player.name}のターン${player.stone}")
        if(!board.putCheck(player.stone)){
            println("パス：${player.name}")
            return
        }
        while(true){
            val(x,y) = player.select(board)
            if(this.board.put(x,y,player.stone))
                //石を設置できたらループを抜ける
                break
            else
                //石を設置できなかったらもう一度
                println("指定した場所には設置できません")
        }
    }
}