package br.com.edsilfer.android.user_onboarding.presenter

import android.animation.ArgbEvaluator
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.edsilfer.android.user_onboarding.R
import br.com.edsilfer.android.user_onboarding.controller.SectionsPagerAdapter
import br.com.edsilfer.kotlin_support.extensions.putProperty


/**
 * Created by efernandes on 28/12/16.
 */
class ActivityUserOnBoarding : AppCompatActivity() {

    companion object {
        val PREF_USER_FIRST_TIME = "PREF_USER_FIRST_TIME"
    }

    private var mNextBtn: ImageButton? = null
    private var mSkipBtn: Button? = null
    private var mViewPager: ViewPager? = null
    private var mFinishBtn: Button? = null

    private var mIndicators = arrayListOf<ImageView>()

    private var mCoordinator: CoordinatorLayout? = null
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private var mCurrentPage = 0
    private var mBackgroundColors = intArrayOf(
            R.color.sampleColor1,
            R.color.sampleColor2,
            R.color.sampleColor3,
            R.color.sampleColor4,
            R.color.sampleColor5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_onboarding)
        instantiateMembers()
        createIndicators()
        initViewPager()
        addOnClickListeners()
    }

    private fun createIndicators() {
        val indicatorContainer = findViewById(R.id.indicator_container) as LinearLayout
        var count = 0
        while (count++ < mBackgroundColors.size) {
            val dot = ImageView(this)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(6, 0, 6, 0)
            dot.layoutParams = params
            indicatorContainer.addView(dot)
            mIndicators.add(dot)
        }
    }

    private fun instantiateMembers() {
        mFinishBtn = findViewById(R.id.finish) as Button
        mNextBtn = findViewById(R.id.next) as ImageButton
        mSkipBtn = findViewById(R.id.skip) as Button

        mViewPager = findViewById(R.id.pager) as ViewPager
        mCoordinator = findViewById(R.id.container) as CoordinatorLayout
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, mBackgroundColors.size)

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            mNextBtn!!.setImageDrawable(
                    paintDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chevron_right), Color.WHITE)
            )
        }
    }

    private fun addOnClickListeners() {
        mNextBtn!!.setOnClickListener({
            mCurrentPage++
            mViewPager!!.setCurrentItem(mCurrentPage, true)
        })

        mSkipBtn!!.setOnClickListener({
            finish()
        })

        mFinishBtn!!.setOnClickListener({
            finish()
            putProperty(PREF_USER_FIRST_TIME, false)
        })
    }

    private fun initViewPager() {
        val evaluator = ArgbEvaluator()
        mViewPager!!.adapter = mSectionsPagerAdapter
        mViewPager!!.currentItem = mCurrentPage

        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                mViewPager!!.setBackgroundColor(
                        evaluator.evaluate(
                                positionOffset,
                                resources.getColor(mBackgroundColors[position]),
                                resources.getColor(mBackgroundColors[if (isLastPage(position)) position else position + 1])
                        ) as Int
                )
            }

            override fun onPageSelected(position: Int) {
                mCurrentPage = position
                updateIndicators(mCurrentPage)
                mViewPager!!.setBackgroundColor(mBackgroundColors[position])
                mNextBtn!!.visibility = if (isLastPage(position)) View.GONE else View.VISIBLE
                mFinishBtn!!.visibility = if (isLastPage(position)) View.VISIBLE else View.GONE
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        updateIndicators(mCurrentPage)
    }

    private fun isLastPage(position: Int): Boolean {
        return position == mBackgroundColors.size - 1
    }

    private fun paintDrawable(drawable: Drawable, color: Int): Drawable {
        var drawableClone = drawable
        drawableClone = DrawableCompat.wrap(drawableClone)
        DrawableCompat.setTint(drawableClone, color)
        DrawableCompat.setTintMode(drawableClone, PorterDuff.Mode.SRC_IN)
        return drawableClone
    }

    private fun updateIndicators(position: Int) {
        var count = 0
        for (i in mIndicators) {
            i.setBackgroundResource(
                    if (count == position) R.drawable.rsc_indicator_activated else R.drawable.rsc_indicator_deactivated
            )
            count++
        }
    }
}