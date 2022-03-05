package othello

enum class Stone(private val label:String) {
    WHITE("●"),BLACK("◯"),AIR("☐");
    /**
     * 自分の裏を返します
     */
    val rearStone: Stone
        get() = when(this){
            WHITE -> BLACK
            BLACK -> WHITE
            AIR -> throw IllegalArgumentException("AIRに裏はない")
        }
    override fun toString() = this.label
}