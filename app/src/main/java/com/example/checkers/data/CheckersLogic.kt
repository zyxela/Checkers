package com.example.checkers.data

import kotlin.math.abs

object CheckersLogic {

    private val blackPieces = mutableListOf<Checker>()
    private val whitePieces = mutableListOf<Checker>()


    private fun delChecker(
        posX: Float,
        posY: Float,
        checkers: MutableList<Checker>
    ): MutableList<Checker> {
        for (i in checkers.indices) {
            if (checkers[i].cordX == posX && checkers[i].cordY == posY) {
                checkers.removeAt(i)
                break
            }
        }
        return checkers
    }

    fun moveTo(checker: Checker, posX: Float, posY: Float) {
        checker.cordX = posX
        checker.cordY = posY
    }

    fun stayMissis() {

    }

    fun getChecker(posX: Float, posY: Float, checkers: List<Checker>): Checker? {
        val ch = checkers.filter {
            (abs(it.cordX - posX) < 65) && (abs(it.cordY - posY) < 65)
        }
        return if (ch.isNotEmpty()) ch[0] else null
    }

    fun kill(
        posFX: Float,
        posFY: Float,
        posTX: Float,
        posTY: Float,
        checkers: MutableList<Checker>
    ): MutableList<Checker> {
        val deltaX = posTX - posFX
        val deltaY = posTY - posFY

        //kp - killed piece [cord]
        var kpX = 0f
        var kpY = 0f

        if (deltaX > 0 && deltaY < 0) {
            kpX = posFX + 130f
            kpY = posFY - 130f

        } else if (deltaX < 0 && deltaY > 0) {
            kpX = posFX - 130f
            kpY = posFY + 130f

        } else if (deltaX > 0 && deltaY > 0) {
            kpX = posFX + 130f
            kpY = posFY + 130f
        } else {
            kpX = posFX - 130f
            kpY = posFY - 130f
        }

        return delChecker(kpX, kpY, checkers)

    }

}