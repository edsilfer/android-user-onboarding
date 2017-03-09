package br.com.edsilfer.android.user_onboarding.presenter.view

import android.annotation.TargetApi
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import br.com.edsilfer.android.user_onboarding.R
import br.com.edsilfer.android.user_onboarding.model.BottomPanelColors
import br.com.edsilfer.kotlin_support.extensions.paintDrawable
import org.jetbrains.anko.textColor

/**
 * Created by efernandes on 28/12/16.
 */

class OnBoardingBottomPanel : RelativeLayout, OnBoardingControlPanel {

    companion object {
        val ARG_INDICATOR_MARGIN = 10
    }

    private var mSkip: Button? = null
    private var mFinish: Button? = null
    private var mNext: ImageButton? = null
    private var mListener: PanelEventListener? = null
    private var mIndicators = arrayListOf<ImageView>()

    private var mSize: Int = 0

    private var mBottomLayoutResource: Int = -1
    private var mIndicatorActiveColor: Int = -1
    private var mIndicatorInactiveColor: Int = -1
    private var mSkipColor: Int = -1
    private var mNextColor: Int = -1
    private var mFinishColor: Int = -1

    // CONSTRUCTORS ================================================================================
    constructor(context: Context, size: Int, bottomPanelColors: BottomPanelColors, bottomLayoutResource: Int) : super(context) {
        mSize = size
        mBottomLayoutResource = bottomLayoutResource
        mIndicatorActiveColor = bottomPanelColors.indicatorActiveColor
        mIndicatorInactiveColor = bottomPanelColors.indicatorInactiveColor
        mSkipColor = bottomPanelColors.skipColor
        mNextColor = bottomPanelColors.nextColor
        mFinishColor = bottomPanelColors.finishColor
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    fun init() {
        val rootView = LayoutInflater.from(context).inflate(mBottomLayoutResource, this, true)

        mFinish = rootView.findViewById(R.id.finish) as Button
        mNext = rootView.findViewById(R.id.next) as ImageButton
        mSkip = rootView.findViewById(R.id.skip) as Button

        mFinish!!.textColor = mFinishColor
        mSkip!!.textColor = mSkipColor
        mNext!!.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_chevron_right).paintDrawable(mNextColor))

        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.addRule(ALIGN_PARENT_BOTTOM)
        params.addRule(ALIGN_PARENT_RIGHT)

        rootView.layoutParams = params

        createIndicators(mSize)
    }

    private fun createIndicators(size: Int) {
        val indicatorContainer = findViewById(R.id.indicator_container) as LinearLayout
        var count = 0
        while (count++ < size) {
            val dot = ImageView(context)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(ARG_INDICATOR_MARGIN, 0, ARG_INDICATOR_MARGIN, 0)
            dot.layoutParams = params
            indicatorContainer.addView(dot)
            mIndicators.add(dot)
        }
    }

    private fun isLastPage(position: Int): Boolean {
        return position == mSize - 1
    }

    // PUBLIC INTERFACE ============================================================================
    override fun updateBar(position: Int) {
        for ((count, i) in mIndicators.withIndex()) {
            i.setImageDrawable(
                    if (count == position) {
                        ContextCompat.getDrawable(context, R.drawable.rsc_indicator_activated).paintDrawable(mIndicatorActiveColor)
                    } else {
                        ContextCompat.getDrawable(context, R.drawable.rsc_indicator_deactivated).paintDrawable(mIndicatorInactiveColor)
                    }
            )
        }

        mNext!!.visibility = if (isLastPage(position)) View.GONE else View.VISIBLE
        mFinish!!.visibility = if (isLastPage(position)) View.VISIBLE else View.GONE
    }

    override fun setListener(listener: PanelEventListener) {
        mListener = listener

        mFinish!!.setOnClickListener {
            mListener!!.onFinishedClicked()
        }

        mNext!!.setOnClickListener {
            mListener!!.onNextClicked()
        }

        mSkip!!.setOnClickListener {
            mListener!!.onSkipClicked()
        }
    }

    interface PanelEventListener {
        fun onFinishedClicked()
        fun onSkipClicked()
        fun onNextClicked()
    }
}

/**
 * CONTRACT
 */
interface OnBoardingControlPanel {
    fun updateBar(position: Int)

    fun setListener(listener: OnBoardingBottomPanel.PanelEventListener)
}
