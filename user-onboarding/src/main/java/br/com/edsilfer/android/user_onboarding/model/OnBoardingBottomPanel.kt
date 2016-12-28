package br.com.edsilfer.android.user_onboarding.model

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import br.com.edsilfer.android.user_onboarding.R
import android.widget.RelativeLayout



/**
 * Created by efernandes on 28/12/16.
 */

class OnBoardingBottomPanel : RelativeLayout {

    companion object {
        val ARG_INDICATOR_MARGIN = 6
    }

    private var mSkip: Button? = null
    private var mFinish: Button? = null
    private var mNext: ImageButton? = null
    private var mListener: PanelEventListener? = null
    private var mIndicators = arrayListOf<ImageView>()

    private var mSize: Int = 0

    constructor(context: Context, size: Int) : super(context) {
        mSize = size
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
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            mNext!!.setImageDrawable(
                    paintDrawable(ContextCompat.getDrawable(context, R.drawable.ic_chevron_right), Color.WHITE)
            )
        }
        mSkip = rootView.findViewById(R.id.skip) as Button

        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)

        rootView.layoutParams = params

        createIndicators(3)
    }

    // TODO: MOVE THIS METHOD TO KOTLIN-ANDROID
    private fun paintDrawable(drawable: Drawable, color: Int): Drawable {
        var drawableClone = drawable
        drawableClone = DrawableCompat.wrap(drawableClone)
        DrawableCompat.setTint(drawableClone, color)
        DrawableCompat.setTintMode(drawableClone, PorterDuff.Mode.SRC_IN)
        return drawableClone
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
    fun updateBar(position: Int) {
        for ((count, i) in mIndicators.withIndex()) {
            i.setBackgroundResource(
                    if (count == position) R.drawable.rsc_indicator_activated else R.drawable.rsc_indicator_deactivated
            )
        }
        mNext!!.visibility = if (isLastPage(position)) View.GONE else View.VISIBLE
        mFinish!!.visibility = if (isLastPage(position)) View.VISIBLE else View.GONE
    }

    fun setListener(listener: PanelEventListener) {
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


    // LISTENER
    interface PanelEventListener {
        fun onFinishedClicked()
        fun onSkipClicked()
        fun onNextClicked()
    }
}
