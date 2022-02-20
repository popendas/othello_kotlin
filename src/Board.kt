class Board(val size:Int) {

    fun test(){
        println("test")
        val stone = Stone.BLACK

        println(stone.rearStone.label)
    }
}