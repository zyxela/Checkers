package com.example.checkers.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.checkers.data.Checker
import com.example.checkers.data.CheckersLogic
import kotlin.math.abs

class Checkerboard(context: Context, attr: AttributeSet) : View(context, attr) {
    private lateinit var paint: Paint
    private var cCheckers: MutableList<Checker> = mutableListOf()
    private var STATEMENT = 1

    private val x_poses = mutableListOf<Float>()
    private val y_poses = mutableListOf<Float>()

    init {
        startPosForCh()
        initPoses()
    }

    lateinit var c: Checker

    var nx = 0f
    var ny = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false

        if (STATEMENT == 1) {
            c = CheckersLogic.getChecker(event.x, event.y, cCheckers) ?: return false
            c.radius += 10
            STATEMENT *= -1
        } else {
            nx = nearest(event.x, x_poses)
            ny = nearest(event.y, y_poses)
            cCheckers = CheckersLogic.kill(c.cordX, c.cordY, nx, ny, cCheckers)
            CheckersLogic.moveTo(c, nx, ny)

            c.radius -= 10
            STATEMENT *= -1
        }
        invalidate()
        return super.onTouchEvent(event)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //
        drawCheckerboard(canvas)
        drawPieces(cCheckers, canvas)

    }

    private fun drawCheckerboard(canvas: Canvas?) {
        paint = Paint()
        var left = 20f
        var top = 20f
        var right = 150f
        var bottom = 150f

        var currColor = 1

        for (i in 1..8) {
            for (j in 1..8) {
                canvas?.drawRect(left, top, right, bottom, paint.apply {
                    if (currColor == 1) {
                        color = Color.LTGRAY
                        currColor *= -1
                    } else {
                        color = Color.BLACK
                        currColor *= -1
                    }
                })
                left += 130f
                right += 130f

            }
            currColor *= -1
            top += 130f
            bottom += 130f
            left = 20f
            right = 150f

        }
    }


    private fun drawPieces(checkers: List<Checker>, canvas: Canvas?) {
        checkers.forEach {
            canvas?.drawCircle(it.cordX, it.cordY, it.radius, paint.apply { color = it.color })
        }
    }

    private fun startPosForCh() {
        var x = 215f
        var y = 85f
        for (i in 0..2) {
            for (j in 0..3) {
                cCheckers.add(Checker(x, y, -7829368, 45f))
                x += 260
            }
            x = if (i % 2 == 0) 85f else 215f
            y += 130
        }

        x = 85f
        y = 735f

        for (i in 0..2) {
            for (j in 0..3) {
                cCheckers.add(Checker(x, y, -1, 45f))
                x += 260
            }
            x = if (i % 2 == 0) 215f else 85f
            y += 130
        }
    }

    private fun initPoses() {
        var x = 85f
        var y = 85f
        for (i in 0..8) {
            x_poses.add(x)
            y_poses.add(y)
            x += 130
            y += 130
        }
    }

    private fun nearest(n: Float, args: MutableList<Float>): Float {
        var nearest = 0f
        var value = 10000f
        for (arg in args)
            if (value > abs(n - arg)) {
                value = abs(n - arg)
                nearest = arg
            }
        return nearest
    }
}