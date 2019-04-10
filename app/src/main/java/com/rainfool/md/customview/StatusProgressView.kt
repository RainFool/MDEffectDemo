package com.rainfool.md.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.rainfool.md.R

/**
 * 带节点的进度条
 * Created by rainfool on 2019/4/9.
 */
class StatusProgressView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "StatusProgressView"
        private const val DEFAULT_LINE_COLOR = Color.BLUE
        private const val DEFAULT_NODE_RADIUS = 16
        private const val DEFAULT_LINE_WIDTH = 4
    }

    // 节点总数目
    private var mNodesCount = 0
    // 当前节点序号
    private var mCurNodesNum = 0

    private var mDrawableProcessing: Drawable?
    private var mDrawableUnreached: Drawable?
    private var mDrawableReached: Drawable?

    private var mNodeRadius = 16
    private var mReachedLineColor = DEFAULT_LINE_COLOR
    private var mUnreachedLineColor = DEFAULT_LINE_COLOR
    private var mLineHeight = 4

    private var mWidth = 0
    private var mHeight = 0

    private var mIsDebugMode = false

    private var mNodeList = listOf<Node>()

    init {
        val mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.StatusProgressView)
        mNodesCount = mTypedArray.getInteger(R.styleable.StatusProgressView_nodesNum, 1) //默认一个节点
        mNodeRadius = mTypedArray.getDimensionPixelSize(R.styleable.StatusProgressView_nodeRadius, DEFAULT_NODE_RADIUS) //节点半径
        mDrawableReached = mTypedArray.getDrawable(R.styleable.StatusProgressView_reachedDrawable)
        mDrawableProcessing = mTypedArray.getDrawable(R.styleable.StatusProgressView_progressingDrawable)
        mDrawableUnreached = mTypedArray.getDrawable(R.styleable.StatusProgressView_unReachedDrawable)
        mCurNodesNum = mTypedArray.getInt(R.styleable.StatusProgressView_currNodeNO, 1)
        mReachedLineColor = mTypedArray.getColor(R.styleable.StatusProgressView_reachedLineColor, DEFAULT_LINE_COLOR)
        mUnreachedLineColor = mTypedArray.getColor(R.styleable.StatusProgressView_unreachedLineColor, DEFAULT_LINE_COLOR)
        mLineHeight = mTypedArray.getDimensionPixelOffset(R.styleable.StatusProgressView_lineHeight, DEFAULT_LINE_WIDTH)
        mIsDebugMode = mTypedArray.getBoolean(R.styleable.StatusProgressView_isDebugMode, false)

        if (mNodesCount <= 1) {
            mNodesCount = 2
        }
        mTypedArray.recycle()

        mNodeList = (0 until mNodesCount).map {
            Node()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
        val nodeWidth = (mWidth - paddingLeft - paddingRight) / (mNodesCount - 1)
        mNodeList.forEachIndexed { index, node ->
            with(node.point) {
                x = when (index) {
                    0 -> paddingLeft
                    mNodeList.size - 1 -> mWidth - paddingRight - mNodeRadius * 2
                    else -> paddingLeft + nodeWidth * index - mNodeRadius
                }
                y = mHeight / 2 - mNodeRadius
            }
            if (index < mCurNodesNum) {
                node.type = 0
            } else if (index > mCurNodesNum) {
                node.type = 2
            } else {
                node.type = 1
            }

        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw")
        drawLines(canvas)
        drawNodes(canvas)

    }

    private fun drawLines(canvas: Canvas) {
        Log.d(TAG, "drawLines")
        val paint = Paint()
        paint.isAntiAlias = true
        val fNodeRadius = mNodeRadius.toFloat()
        val fHeight = mHeight.toFloat()
        val fLineHeight = mLineHeight.toFloat()
        val currentNode = mNodeList[mCurNodesNum]
        paint.color = mReachedLineColor
        paint.strokeWidth = fLineHeight

        val lineY = fHeight / 2 - fLineHeight / 2F
        val reachedLineStartX = paddingLeft + fNodeRadius
        val reachedLineStopX = currentNode.point.x + fNodeRadius
        val unreachedLineStopX = mWidth - fNodeRadius - paddingRight

        canvas.drawLine(reachedLineStartX, lineY, reachedLineStopX, lineY, paint)
        paint.color = mUnreachedLineColor
        canvas.drawLine(reachedLineStopX, lineY, unreachedLineStopX, lineY, paint)
    }

    private fun drawNodes(canvas: Canvas) {
        mNodeList.forEachIndexed { index, node ->
            Log.v("drawNodes", "x=${node.point.x},y=${node.point.y}")
            node.run {
                val pendingDrawable: Drawable? =
                        when {
                            index < mCurNodesNum -> mDrawableReached
                            index == mCurNodesNum -> mDrawableProcessing
                            else -> mDrawableUnreached
                        }
                pendingDrawable?.setBounds(point.x, point.y, point.x + mNodeRadius * 2, point.y + mNodeRadius * 2)
                pendingDrawable?.draw(canvas)
                if (mIsDebugMode) {
                    val paint = Paint()
                    paint.style = Paint.Style.STROKE
                    canvas.drawRect(point.x.toFloat(), point.y.toFloat(), (point.x + mNodeRadius * 2).toFloat(), (point.y + mNodeRadius * 2).toFloat(), paint)
                    canvas.drawLine(point.x.toFloat() + mNodeRadius, 0F, point.x.toFloat() + mNodeRadius, mHeight.toFloat(), paint)
                    canvas.drawLine(0F, mHeight / 2F, mWidth.toFloat(), mHeight / 2F, paint)
                }
            }
        }
    }

    data class Node(val point: Point = Point(), var type: Int = 0)

}