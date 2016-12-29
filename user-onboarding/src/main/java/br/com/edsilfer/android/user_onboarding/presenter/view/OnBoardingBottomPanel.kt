package br.com.edsilfer.android.user_onboarding.presenter.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import br.com.edsilfer.android.user_onboarding.R
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
    private var mResColor: Int = 0

    // CONSTRUCTORS ================================================================================
    constructor(context: Context, size: Int, colorResource: Int = -1, colorString: String = "") : super(context) {
        mSize = size
        if (colorString.isEmpty() && colorResource != -1) {
            mResColor = context.resources.getColor(colorResource)
        } else if (colorResource == -1 && !colorString.isEmpty()) {
            mResColor = Color.parseColor(colorString)
        } else {
            mResColor = android.R.color.white
        }
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
        val rootView = LayoutInflater.from(context).inflate(R.layout.util_bottom_toolbar, this, true)

        mFinish = rootView.findViewById(R.id.finish) as Button
        mNext = rootView.findViewById(R.id.next) as ImageButton
        mSkip = rootView.findViewById(R.id.skip) as Button

        mFinish!!.textColor = mResColor
        mSkip!!.textColor = mResColor
        mNext!!.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_chevron_right).paintDrawable(mResColor))

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
                        ContextCompat.getDrawable(context, R.drawable.rsc_indicator_activated).paintDrawable(mResColor)
                    } else {
                        ContextCompat.getDrawable(context, R.drawable.rsc_indicator_deactivated).paintDrawable(mResColor)
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
