package com.example.checkers.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Checkerboard(context: Context, attr: AttributeSet) : View(context, attr) {
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //
        drawCheckerboard(canvas)


    }

    private fun drawCheckerboard(canvas: Canvas?){
        val paint = Paint()
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
}