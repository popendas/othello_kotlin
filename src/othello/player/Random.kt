package othello.player

import othello.Stone


class Random(stone: Stone, name: String) : Player(stone, name) {
    /**
     * ランダムにボードの場所を1つ選択し戻します。
     * 選択された場所に必ずしも石を設置できるかどうかはわかりません。
     *
     * @param board Stone型の二次配列
     * @return 選択したx座標とy座標のPair
     */
    override fun select(board: Array<Array<Stone>>): Pair<Int, Int> {
        val range = board.indices
        val x = range.random()
        val y = range.random()
        return x to y
    }
}