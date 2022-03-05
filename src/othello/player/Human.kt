package othello.player

import othello.Board
import othello.Stone

/**
 * 標準入力で設置場所の選択を行うクラス
 *
 * @param stone プレイヤーに持たせる石
 * @param name 名前
 */
class Human(stone: Stone, name: String) : Player(stone, name) {
    /**
     * 標準入力で石の置く場所を指定します。
     * 選択された場所に必ずしも石を設置できるかどうかはわかりません。
     * @param board Stone型の二次配列
     * @return 選択したx座標とy座標のPair
     */
    override fun select(board: Board): Pair<Int, Int> {
        val x = readInt("横を指定してください(1~${board.size})>") - 1
        val y = readInt("縦を指定してください(1~${board.size})>") - 1
        return x to y
    }

    private fun readInt(msg: String): Int {
        print(msg)
        var num = readLine()?.toIntOrNull()
        while (num == null) {
            print("数字を入力してください")
            num = readLine()?.toIntOrNull()
        }
        return num
    }

}