package com.rainfool.md.customview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
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
        private const val DEFAULT_NODE_NAME_MARGIN_TOP = 12
        private const val DEFAULT_NODE_NAME_TEXT_SIZE = 12F
        private const val DEFAULT_NODE_NAME_TEXT_COLOR = Color.BLACK
    }

    // 节点总数目
    private var mNodesCount = 0
    // 当前节点序号
    private var mCurStep = 0
    // 当前提示信息位置
    private var mCurTipIndex = 0

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

    private var mNodeNameList = listOf<String>()
    private var mNodeNameMarginTop = DEFAULT_NODE_NAME_MARGIN_TOP
    private var mNodeNameTextSize = DEFAULT_NODE_NAME_TEXT_SIZE
    private var mReachedNodeTextColor = DEFAULT_NODE_NAME_TEXT_COLOR
    private var mUnreachedNodeTextColor = DEFAULT_NODE_NAME_TEXT_COLOR
    private var mProgressingNodeTextColor = DEFAULT_NODE_NAME_TEXT_COLOR
    private var mProgressingTipTextColor = DEFAULT_NODE_NAME_TEXT_COLOR
    private var mProgressingTipTextSize = DEFAULT_NODE_NAME_TEXT_SIZE
    private var mProgressingTipText: CharSequence = ""

    private var mNodeList = listOf<Node>()

    private var mPaint = Paint()
    private var mTextFontMetircs = Paint.FontMetrics()
    private val mRect: Rect = Rect()


    init {
        val mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.StatusProgressView)
        mNodesCount = mTypedArray.getInteger(R.styleable.StatusProgressView_nodesNum, 1) //默认一个节点
        mNodeRadius = mTypedArray.getDimensionPixelSize(R.styleable.StatusProgressView_nodeRadius, DEFAULT_NODE_RADIUS) //节点半径
        mDrawableReached = mTypedArray.getDrawable(R.styleable.StatusProgressView_reachedDrawable)
        mDrawableProcessing = mTypedArray.getDrawable(R.styleable.StatusProgressView_progressingDrawable)
        mDrawableUnreached = mTypedArray.getDrawable(R.styleable.StatusProgressView_unReachedDrawable)
        mCurStep = mTypedArray.getInt(R.styleable.StatusProgressView_curStep, 0)
        mReachedLineColor = mTypedArray.getColor(R.styleable.StatusProgressView_reachedLineColor, DEFAULT_LINE_COLOR)
        mUnreachedLineColor = mTypedArray.getColor(R.styleable.StatusProgressView_unreachedLineColor, DEFAULT_LINE_COLOR)
        mLineHeight = mTypedArray.getDimensionPixelSize(R.styleable.StatusProgressView_lineHeight, DEFAULT_LINE_WIDTH)
        mIsDebugMode = mTypedArray.getBoolean(R.styleable.StatusProgressView_isDebugMode, false)
        val nodeNameArray = mTypedArray.getTextArray(R.styleable.StatusProgressView_nodeNameArray)
        if (nodeNameArray != null) {
            mNodeNameList = nodeNameArray.map {
                it.toString()
            }
        }
        mNodeNameMarginTop = mTypedArray.getDimensionPixelOffset(R.styleable.StatusProgressView_nodeNameMarginTop, DEFAULT_NODE_NAME_MARGIN_TOP)
        mNodeNameTextSize = mTypedArray.getDimension(R.styleable.StatusProgressView_nodeNameTextSize, DEFAULT_NODE_NAME_TEXT_SIZE)
        mReachedNodeTextColor = mTypedArray.getColor(R.styleable.StatusProgressView_reachedNodeTextColor, DEFAULT_NODE_NAME_TEXT_COLOR)
        mUnreachedNodeTextColor = mTypedArray.getColor(R.styleable.StatusProgressView_unreachedNodeTextColor, DEFAULT_NODE_NAME_TEXT_COLOR)
        mProgressingNodeTextColor = mTypedArray.getColor(R.styleable.StatusProgressView_progressingNodeTextColor, DEFAULT_NODE_NAME_TEXT_COLOR)
        mProgressingTipTextColor = mTypedArray.getColor(R.styleable.StatusProgressView_progressingTipTextColor, DEFAULT_NODE_NAME_TEXT_COLOR)
        mProgressingTipTextSize = mTypedArray.getDimension(R.styleable.StatusProgressView_progressingTipTextSize, DEFAULT_NODE_NAME_TEXT_SIZE)
        mProgressingTipText = mTypedArray.getString(R.styleable.StatusProgressView_progressingTipText) ?: ""

        if (mNodesCount <= 1) {
            mNodesCount = 2
        }
        mTypedArray.recycle()

        mNodeList = (0 until mNodesCount).map {
            Node()
        }
    }

    fun setCurrentStep(step: Int) {
        mCurStep = when {
            step < 0 -> -1
            step > mNodesCount -> mNodesCount
            else -> step
        }
        Log.d(TAG, "setCurrentStep ,step:$mCurStep")
        invalidate()
    }

    fun setCurrentTip(index: Int, tipText: CharSequence) {
        if (index < 0 || index > mNodesCount) {
            Log.e(TAG, "index is Illegal")
            return
        }
        Log.d(TAG, "setCurrentTip,index:$index,tip:$tipText")
        mCurTipIndex = index
        mProgressingTipText = tipText
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (mNodeNameList.isNotEmpty() && layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
            mPaint.textSize = mNodeNameTextSize
            mTextFontMetircs = mPaint.fontMetrics
            val customHeightSize = paddingTop + mNodeRadius * 2 + mNodeNameMarginTop + (mTextFontMetircs.bottom - mTextFontMetircs.top) + paddingBottom
            setMeasuredDimension(widthSize, customHeightSize.toInt())
        }

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
                y = paddingTop
            }
            if (index < mCurStep) {
                node.type = 0
            } else if (index > mCurStep) {
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
        val currentNode = if (mCurStep >= mNodesCount) {
            mNodeList.last()
        } else if (mCurStep < 0) {
            mNodeList.first()
        } else {
            mNodeList[mCurStep]
        }
        mPaint.reset()
        mPaint.isAntiAlias = true
        val fNodeRadius = mNodeRadius.toFloat()
        val fLineHeight = mLineHeight.toFloat()
        mPaint.color = mReachedLineColor
        mPaint.strokeWidth = fLineHeight

        val lineY = paddingTop + mNodeRadius + 0F
        val reachedLineStartX = paddingLeft + fNodeRadius
        val reachedLineStopX = currentNode.point.x + fNodeRadius
        val unreachedLineStopX = mWidth - fNodeRadius - paddingRight

        canvas.drawLine(reachedLineStartX, lineY, reachedLineStopX, lineY, mPaint)
        mPaint.color = mUnreachedLineColor
        canvas.drawLine(reachedLineStopX, lineY, unreachedLineStopX, lineY, mPaint)
    }

    private fun drawNodes(canvas: Canvas) {
        mNodeList.forEachIndexed { index, node ->
            Log.v("drawNodes", "x=${node.point.x},y=${node.point.y}")
            drawNode(canvas, index, node)
            drawText(canvas, index, node)
            drawDebugLine(node, canvas)
            if (index == mCurTipIndex) {
                drawTipText(canvas, node)
            }
        }

    }

    private fun drawNode(canvas: Canvas, index: Int, node: Node) {
        mPaint.reset()
        node.run {
            val pendingDrawable: Drawable? =
                    when {
                        index < mCurStep -> mDrawableReached
                        index == mCurStep -> mDrawableProcessing
                        else -> mDrawableUnreached
                    }
            pendingDrawable?.setBounds(point.x, point.y, point.x + mNodeRadius * 2, point.y + mNodeRadius * 2)
            pendingDrawable?.draw(canvas)
        }
    }

    private fun drawText(canvas: Canvas, index: Int, node: Node) {
        if (mNodeNameList.size != mNodesCount) {
            Log.e(TAG, "drawText not work because node name list not equals node count")
            return
        }
        mPaint.reset()
        mPaint.isAntiAlias = true
        val textColor =
                when {
                    index < mCurStep -> mReachedNodeTextColor
                    index == mCurStep -> mProgressingNodeTextColor
                    else -> mUnreachedNodeTextColor
                }
        val text = mNodeNameList[index]
        mPaint.textSize = mNodeNameTextSize
        mPaint.color = textColor
        mPaint.getTextBounds(text, 0, text.length, mRect)
        val startX = node.point.x + mNodeRadius - mRect.width() / 2
        val startY = node.point.y + mNodeRadius * 2 + mNodeNameMarginTop + mRect.height()
        canvas.drawText(text, 0, text.length, startX.toFloat(), startY.toFloat(), mPaint)
    }

    private fun drawTipText(canvas: Canvas, node: Node) {
        if (mProgressingTipText.isEmpty()) {
            return
        }
        mPaint.reset()
        mPaint.isAntiAlias = true
        mPaint.textSize = mProgressingTipTextSize
        mPaint.color = mProgressingTipTextColor
        val text = mProgressingTipText.toString()
        mPaint.getTextBounds(text, 0, text.length, mRect)
        val startX = node.point.x + mNodeRadius - mRect.width() / 2
        // 系数为行距
        val startY = node.point.y - mRect.height() * 0.8
        canvas.drawText(text, 0, text.length, startX.toFloat(), startY.toFloat(), mPaint)
    }

    private fun drawDebugLine(node: Node, canvas: Canvas) {
        if (mIsDebugMode) {
            mPaint.reset()
            node.run {
                mPaint.style = Paint.Style.STROKE
                canvas.drawRect(point.x.toFloat(), point.y.toFloat(), (point.x + mNodeRadius * 2).toFloat(), (point.y + mNodeRadius * 2).toFloat(), mPaint)
                canvas.drawLine(point.x.toFloat() + mNodeRadius, 0F, point.x.toFloat() + mNodeRadius, mHeight.toFloat(), mPaint)
                canvas.drawLine(0F, mHeight / 2F, mWidth.toFloat(), mHeight / 2F, mPaint)
            }
        }
    }

    data class Node(val point: Point = Point(), var type: Int = 0)

}