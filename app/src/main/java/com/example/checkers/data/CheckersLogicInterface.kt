package com.example.checkers.data

interface CheckersLogicInterface {

    fun delChecker(checker: Checker)

    fun moveTo(checker: Checker, posX: Float, posY:Float)

    fun stayMissis()

}