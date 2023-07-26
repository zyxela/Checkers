package com.example.checkers.data

import kotlin.math.abs

class CheckersLogic : CheckersLogicInterface {

    private val blackPieces = mutableListOf<Checker>()
    private val whitePieces = mutableListOf<Checker>()



    override fun delChecker(checker: Checker) {

    }

    override fun moveTo(checker: Checker, posX: Float, posY: Float) {

    }

    override fun stayMissis() {

    }

    fun editChecker(posX: Float, posY: Float, move: Int, unit: (Checker) -> Unit) {
        (if (move == 1) whitePieces else blackPieces).filter {
            abs(it.cordX - posX) > 20
            abs(it.cordY - posY) > 20
        }[0].apply {
            unit(this)
        }

    }
}