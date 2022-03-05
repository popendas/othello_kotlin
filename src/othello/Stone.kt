package othello

enum class Stone(private val label:String) {
    WHITE("●"),BLACK("◯"),AIR("☐");
    val rearStone: Stone
        get() = when(this){
            WHITE -> BLACK
            BLACK -> WHITE
            AIR -> throw IllegalArgumentException("AIRに裏はない")
        }
    override fun toString() = this.label
}