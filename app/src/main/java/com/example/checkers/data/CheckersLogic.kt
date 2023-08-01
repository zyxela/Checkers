package com.example.checkers.data

import kotlin.math.abs

class CheckersLogic(var checkers: MutableList<Checker>) {
    private val MISS_SIZE = 60f

    var countOfWhite = 0
    var countOfBlack = 0

    var queue = 1

    fun setCountOfPieces(countOfBlack: Int, countOfWhite: Int) {
        this.countOfBlack = countOfBlack
        this.countOfWhite = countOfWhite
    }

    fun gameOverAlert(): Int {
        if (countOfBlack == 0 || countOfWhite == 0)
            return 0
        return 1
    }

    private fun delChecker(
        walkingCh: Checker,
        posX: Float,
        posY: Float,
    ): MutableList<Checker> {
        for (i in checkers.indices) {
            if (checkers[i].cordX == posX && checkers[i].cordY == posY && walkingCh.color != checkers[i].color) {
                if (checkers[i].color == -1)
                    --countOfWhite
                else
                    --countOfBlack

                checkers.removeAt(i)
                break
            }
        }
        return checkers
    }

    fun moveTo(checker: Checker, posX: Float, posY: Float) {

        if (getChecker(posX, posY) == null) {
            if (abs(checker.cordX - posX) >= 260f)
                kill(checker, posX, posY)

            if (checker.cordX != posX && checker.cordY != posY)
                queue *= -1
            checker.cordX = posX
            checker.cordY = posY

            if (checker.cordY == 85f && checker.color == -1) {
                if (checker.radius != MISS_SIZE)
                    checker.radius = MISS_SIZE
            } else if (checker.cordY == 545f && checker.color != -1) {
                if (checker.radius != MISS_SIZE)
                    checker.radius = MISS_SIZE
            }
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

        if (deltaX > 0f && deltaY < 0f) {  //R U
            kpX = posTX - 130f
            kpY = posTY + 130f

        } else if (deltaX < 0f && deltaY > 0f) {// L D
            kpX = posTX + 130f
            kpY = posTY - 130f

        } else if (deltaX > 0f && deltaY > 0f) {// R D
            kpX = posTX - 130f
            kpY = posTY - 130f
        } else if (deltaX < 0f && deltaY < 0f) {// L U
            kpX = posTX + 130f
            kpY = posTY + 130f
        }

        this.checkers = delChecker(ch, kpX, kpY)

    }
}