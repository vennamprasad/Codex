package com.android.codex.layout

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.android.codex.R
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import timber.log.Timber

class SkeletonLoaderLayout(
    context: Context,
    private val attrSet: AttributeSet?
) : ConstraintLayout(context, attrSet) {

    private lateinit var shimmer: ShimmerFrameLayout

    var isSkeletonCreated = false

    init {
        setLayout()
    }

    private fun setLayout(
        attrs: AttributeSet? = attrSet,
        skeletonVisibility: Boolean = true
    ) {
        val attributes: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SkeletonLoaderConstraintLayout,
            0,
            0
        )
        try {
            if (skeletonVisibility) {
                if (isSkeletonCreated.not()) {
                    val skeletonLayout = attributes.getResourceId(
                        R.styleable.SkeletonLoaderConstraintLayout_skeletonLayout, 0
                    )
                    val shimmerChild = inflate(context, skeletonLayout, null)
                    shimmer = ShimmerFrameLayout(context, null).apply {
                        addView(shimmerChild)
                    }
                    shimmer.setShimmer(
                        Shimmer.ColorHighlightBuilder()
                            .setAutoStart(true)
                            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                            .setDuration(600)
                            .setTilt(0f)
                            .setBaseAlpha(0.4f)
                            .setRepeatMode(ValueAnimator.INFINITE)
                            .setRepeatDelay(1000)
                            .setHighlightAlpha(0.15f)
                            .setWidthRatio(1.5f)
                            .setShape(Shimmer.Shape.LINEAR)
                            .setBaseColor(
                                ContextCompat
                                .getColor(context, R.color.gray))
                            .setHighlightColor(ContextCompat
                                .getColor(context, R.color.white))
                            .setHeightRatio(1f)
                            .setDropoff(1f)
                            .build()
                    )
                    shimmer.showShimmer(skeletonVisibility)
                    addView(shimmer)
                    isSkeletonCreated = true
                }
            } else {
                shimmer.visibility = View.GONE
                shimmer.stopShimmer()
                shimmer.hideShimmer()
            }
        } catch (ex: Exception) {
            Timber.d("setLayout: $ex")
        } finally {
            attributes.recycle()
        }
    }

    fun setSkeletonVisibility(skeletonVisibility: Boolean) {
        setLayout(skeletonVisibility = skeletonVisibility)
    }

    companion object {
        private const val TAG = "SkeletonLoader"
    }
}
