package com.rainfool.md.shape

import android.content.Context
import android.graphics.Color
import android.graphics.RadialGradient
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.graphics.drawable.PaintDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import java.util.concurrent.atomic.AtomicInteger

/**
 * 车队背景图片生成
 * @author krystian
 */
class TeamCardBackgroundFactory(private val context: Context) {

    companion object {
        /**
         * 车队背景颜色色值
         */
        private val COLOR_ARRAY = arrayOf(
                Pair("#BDDCF7", "#F0FBFE"),
                Pair("#C4E5EF", "#F2FAFC"),
                Pair("#FFDEED", "#FFF9FC"),
                Pair("#D1E1F9", "#F6FAFF"),
                Pair("#FAEBD4", "#FFFCF6"),
                Pair("#E4E0F8", "#F9F8FF")
        )

    }

    // 颜色计算标记
    var colorIndex = AtomicInteger(0)
        private set

    fun size() = COLOR_ARRAY.size

    fun next(): Drawable {
        val index = colorIndex.getAndIncrement()
        return generateDrawableFromIndex(index)
    }

    fun generateDrawableFromIndex(index: Int): PaintDrawable {
        val colorPair = COLOR_ARRAY[index % COLOR_ARRAY.size]
        val firstColor = Color.parseColor(colorPair.first)
        val secondColor = Color.parseColor(colorPair.second)
        return radialGradientBackground(firstColor, secondColor)
    }

    private fun radialGradientBackground(vararg colors: Int, positionX: Float = 0.25f, positionY: Float = 0.25f,
                                         size: Float = 0.8f): PaintDrawable {
        val radialGradientBackground = PaintDrawable()
        radialGradientBackground.shape = RectShape()
        radialGradientBackground.setCornerRadius(5f)
        radialGradientBackground.shaderFactory = RadialShaderFactory(colors, positionX, positionY, size)
        return radialGradientBackground
    }

    private class RadialShaderFactory(private val colors: IntArray, val positionX: Float,
                                      val positionY: Float, val size: Float) : ShapeDrawable.ShaderFactory() {

        override fun resize(width: Int, height: Int): Shader {
            return RadialGradient(width * positionX, height * positionY,
                    minOf(width, height) * size, colors, null, Shader.TileMode.CLAMP)
        }
    }
}