package com.example.newsapplication.common.loader

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout

class DotProgressBar : FrameLayout {
    private var margin: Int = convertDpToPixel(4f, context)
    private var dotRadius: Int = convertDpToPixel(8f, context)
    private var numberOfDots = LOADER_NUMBER_OF_DOTS
    private val animators = mutableListOf<Animator>()
    private var animationDuration = LOADER_ANIMATION_DURATION
    private var minScale = LOADER_DOTS_MIN_SCALE
    private var maxScale = LOADER_DOTS_MAX_SCALE
    private var primaryAnimator: ValueAnimator? = null
    private lateinit var dotProgressBar: LinearLayout
    private var dotAnimator: ValueAnimator? = null
    private var colorCodes: ArrayList<Int>? = null


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    ) {
        init()
    }

    private fun init() {
        clipChildren = false
        clipToPadding = false
        dotProgressBar = LinearLayout(context)
        val progressBarLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        progressBarLayoutParams.gravity = Gravity.CENTER
        dotProgressBar.layoutParams = progressBarLayoutParams
        dotProgressBar.clipChildren = false
        dotProgressBar.clipToPadding = false
        addView(dotProgressBar)
        animators.clear()
        for (i in 0 until numberOfDots) {
            colorCodes?.getOrNull(i)?.let {
                val dot = CircleView(context, dotRadius, it, true)
                dot.scaleX = minScale
                dot.scaleY = minScale
                dotProgressBar.addView(dot)
                val animator = getScaleAnimator(dot)
                animators.add(animator)
            }
        }
        primaryAnimator?.cancel()
        primaryAnimator = ValueAnimator.ofInt(0, numberOfDots)
        primaryAnimator?.addUpdateListener {
            if (it.animatedValue != numberOfDots)
                animators[it.animatedValue as Int].start()
        }
        primaryAnimator?.repeatMode = ValueAnimator.RESTART
        primaryAnimator?.repeatCount = ValueAnimator.INFINITE
        primaryAnimator?.duration = animationDuration
        primaryAnimator?.interpolator = LinearInterpolator()
    }

    private fun getScaleAnimator(view: View): Animator {
        if (dotAnimator != null)
            return dotAnimator as ValueAnimator
        val animator = ValueAnimator.ofFloat(minScale, maxScale)
        animator.addUpdateListener {
            view.scaleX = it.animatedValue as Float
            view.scaleY = it.animatedValue as Float
        }
        animator.duration = animationDuration / numberOfDots.toLong()
        animator.repeatCount = 1
        animator.repeatMode = ValueAnimator.REVERSE
        animator.interpolator = LinearInterpolator()
        return animator
    }

    fun stopAnimation() {
        primaryAnimator?.cancel()
    }

    fun startAnimation() {
        primaryAnimator?.start()
    }


    override fun setVisibility(visibility: Int) {
        if (visibility == View.VISIBLE) startAnimation()
        else stopAnimation()
        super.setVisibility(visibility)
    }

    class Builder {
        private var margin = 4
        private var dotRadius = 8
        private var numberOfDots = 3
        private var animationDuration = 1000L
        private var minScale = 0.5f
        private var maxScale = 0.7f
        private var primaryAnimator: ValueAnimator? = null
        private var colorCodes: ArrayList<Int>? = null


        fun build(context: Context): DotProgressBar {
            val dotProgressBar = DotProgressBar(context)
            dotProgressBar.maxScale = maxScale
            dotProgressBar.minScale = minScale
            dotProgressBar.numberOfDots = numberOfDots
            dotProgressBar.animationDuration = animationDuration
            dotProgressBar.margin = convertDpToPixel(margin.toFloat(), context)
            dotProgressBar.dotRadius = convertDpToPixel(dotRadius.toFloat(), context)
            dotProgressBar.primaryAnimator = primaryAnimator
            dotProgressBar.colorCodes = colorCodes
            dotProgressBar.init()
            return dotProgressBar
        }
        

        fun setdotRadius(dotRadius: Int): Builder {
            this.dotRadius = dotRadius
            return this
        }

        fun setNumberOfDots(numberOfDots: Int): Builder {
            this.numberOfDots = numberOfDots
            return this
        }

        fun setColorCodes(colors: ArrayList<Int>): Builder {
            this.colorCodes = colors
            return this
        }

        fun setMaxScale(maxScale: Float): Builder {
            this.maxScale = maxScale
            return this
        }

        fun setMinScale(minScale: Float): Builder {
            this.minScale = minScale
            return this
        }

        fun setAnimationDuration(duration: Long): Builder {
            this.animationDuration = duration
            return this
        }
    }

    companion object {
        fun convertDpToPixel(dp: Float, context: Context): Int {
            return (dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
        }
    }


}