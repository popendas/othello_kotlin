package othello

import java.util.StringJoiner


class Board(val size: Int) {
    private val board: Array<Array<Stone>> = Array(size) { Array(size) { Stone.AIR } }

    init {
        //ボードに石を設置
        val putArea = size / 2
        this.board[putArea][putArea] = Stone.WHITE
        this.board[putArea - 1][putArea - 1] = Stone.WHITE
        this.board[putArea - 1][putArea] = Stone.BLACK
        this.board[putArea][putArea - 1] = Stone.BLACK
    }

    val indices get() = this.board.indices

    val isEnd get() = !(putCheck(Stone.BLACK) || putCheck(Stone.WHITE))

    /**
     * オセロ盤の指定した座標に指定した石を設置します。<br>
     * 石を設置できたらtrue、それ以外はfalseを戻します。<br>
     * また、チェックモードをtrueにすると石を設置しません。戻し値には影響しません。
     *
     * @param x X座標
     * @param y Y座標
     * @param stone 置く石の種類
     * @param checkMode trueにすると石を設置しません
     * @return 石を設置できるならtrue、それ以外はfalse
     */
    fun put(x: Int, y: Int, stone: Stone, checkMode: Boolean = false): Boolean {
        if (!(x in this.board.indices && y in this.board.indices) || (this.board[y][x] != Stone.AIR)) {
            //指定された座標がボードに収まってないか、すでに石が置かれていたら戻す
            return false
        }
        var result = false
        //8方向を調べる
        for (i in -1..1)
            for (j in -1..1)
                result = putVector(Pointer(x, y, i, j), stone, checkMode) || result
        return result
    }

    /**
     * 指定した石を設置できる場所があるか判定します。
     * 設置できる場所があるならtrue、それ以外はfalse
     *
     * @param stone 確かめる石の種類
     * @return 一つでも石を設置できる場所があるならtrue、それ以外はfalse
     */
    fun putCheck(stone: Stone): Boolean {
        for (i in board.indices)
            for (j in board.indices)
                if (put(j, i, stone, true))
                    return true
        return false
    }

    private fun putVector(pointer: Pointer, stone: Stone, checkMode: Boolean): Boolean {
        if (pointer.xVector == 0 && pointer.yVector == 0) {
            //ベクトルが二つとも0なら処理をしない
            return false
        }
        //ポインターがボードに収まっているか確かめる関数
        fun checkInBoard() = (pointer.y in board.indices) && (pointer.x in board.indices)

        //石を置けるかチェックする
        //石の裏側を取得
        val rearStone = stone.rearStone
        //一つ先が
        pointer.next()
        if (checkInBoard() && this.board[pointer.y][pointer.x] != rearStone) {
            //敵の石じゃないorボード外
            return false
        }

        //敵の石がなくなるまでポインターを進める
        pointer.next()
        while (checkInBoard() && (this.board[pointer.y][pointer.x] == rearStone))
            pointer.next()

        if (!checkInBoard() || (this.board[pointer.y][pointer.x] != stone)) {
            //ボードの範囲外ならor自分の石でないなら
            return false
        }

        if (!checkMode) {
            //チェックモードがオフなら石を置く
            while (!pointer.isZero) {
                pointer.back()
                this.board[pointer.y][pointer.x] = stone
            }
        }

        return true
    }

    override fun toString(): String {
        val result = StringJoiner("\n")
        //全角の番号を作成
        result.add("　" + ('１'..(this.board.size + '０'.code).toChar()).joinToString(""))
        for ((i, line) in this.board.withIndex()) {
            result.add((i + '１'.code).toChar() + line.joinToString(""))
        }
        return result.toString()
    }


}

private class Pointer(val defaultX: Int, val defaultY: Int, val xVector: Int, val yVector: Int) {
    var i: Int = 0

    var x: Int = 0
        private set

    var y: Int = 0
        private set

    val isZero
        get() = i == 0

    init {
        reset()
    }

    fun reset() {
        i = 0
        update()
    }

    fun next() {
        i++
        update()
    }

    fun back() {
        i--
        update()
    }

    private fun update() {
        x = defaultX + (i * xVector)
        y = defaultY + (i * yVector)
    }


}