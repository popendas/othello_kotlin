package othello.player

import othello.Board
import othello.Stone

/**
 * 石を置く場所をランダムで決めるクラス
 *
 * @param stone プレイヤーに持たせる石
 * @param name 名前
 */
class Random(stone: Stone, name: String) : Player(stone, name) {
    /**
     * ランダムにボードの場所を1つ選択し戻します。
     *
     * @param board Stone型の二次配列
     * @return 選択したx座標とy座標のPair
     */
    override fun select(board: Board): Pair<Int, Int> {
        print("エンターで進む")
        readLine()
        val putList = board.getPutList(this.stone)
        return if(putList.isEmpty()) 0 to 0 else putList[putList.indices.random()]
    }
}