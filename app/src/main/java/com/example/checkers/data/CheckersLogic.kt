package com.example.checkers.data

import kotlin.math.abs

class CheckersLogic(var checkers: MutableList<Checker>) {

    private fun delChecker(
        walkingCh: Checker,
        posX: Float,
        posY: Float,
    ): MutableList<Checker> {
        for (i in checkers.indices) {
            if (checkers[i].cordX == posX && checkers[i].cordY == posY && walkingCh.color != checkers[i].color) {
                checkers.removeAt(i)
                break
            }
        }
        return checkers
    }

    fun moveTo(checker: Checker, posX: Float, posY: Float) {

        if (getChecker(posX, posY) == null) {
            if (abs(checker.cordX-posX)==260f)
                kill(checker, posX,posY)

            checker.cordX = posX
            checker.cordY = posY
        }

    }

    fun getChecker(posX: Float, posY: Float): Checker? {
        val ch = checkers.filter {
            (abs(it.cordX - posX) < 65) && (abs(it.cordY - posY) < 65)
        }
        return if (ch.isNotEmpty()) ch[0] else null
    }

    private fun kill(
        ch: Checker,
        posTX: Float,
        posTY: Float,
    ) {
        val posFX = ch.cordX
        val posFY = ch.cordY
        val deltaX = posTX - posFX
        val deltaY = posTY - posFY

        //kp - killed piece [cord]
        var kpX = 0f
        var kpY = 0f

        if (deltaX == 260f && deltaY == -260f) {  //
            kpX = posFX + 130f
            kpY = posFY - 130f

        } else if (deltaX == -260f && deltaY == 260f) {
            kpX = posFX - 130f
            kpY = posFY + 130f

        } else if (deltaX == 260f && deltaY == 260f) {
            kpX = posFX + 130f
            kpY = posFY + 130f
        } else if (deltaX == -260f && deltaY == -260f) {
            kpX = posFX - 130f
            kpY = posFY - 130f
        }

        this.checkers = delChecker(ch, kpX, kpY)

    }

}