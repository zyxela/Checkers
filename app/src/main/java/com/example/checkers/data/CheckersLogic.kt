package com.example.checkers.data

import kotlin.math.abs

object CheckersLogic : CheckersLogicInterface {

    private val blackPieces = mutableListOf<Checker>()
    private val whitePieces = mutableListOf<Checker>()


    override fun delChecker(checker: Checker) {

    }

    override fun moveTo(checker: Checker, posX: Float, posY: Float) {
        checker.cordX = posX
        checker.cordY = posY
    }

    override fun stayMissis() {

    }

    fun getChecker(posX: Float, posY: Float, checkers: List<Checker>): Checker? {
       val ch = checkers.filter {
            (abs(it.cordX - posX) < 120) && (abs(it.cordY - posY) < 120)
        }
       return if (ch.isNotEmpty()) ch[0] else null
    }
}