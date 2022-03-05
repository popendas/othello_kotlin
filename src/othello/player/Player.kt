package othello.player

import othello.Board
import othello.Stone

abstract class Player(val stone: Stone,val name:String) {
    /**
     * ボードの状態から石を置く場所を選択し返します。
     * 選択された場所に必ずしも石を設置できるかどうかはわかりません。
     * @param board Stone型の二次配列
     * @return 選択したx座標とy座標のPair
     */
    abstract fun select(board:Board):Pair<Int,Int>

}