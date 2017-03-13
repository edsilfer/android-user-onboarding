package br.com.edsilfer.android.user_onboarding.presenter

import android.animation.ArgbEvaluator
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.RelativeLayout
import br.com.edsilfer.android.user_onboarding.R
import br.com.edsilfer.android.user_onboarding.controller.SectionsPagerAdapter
import br.com.edsilfer.android.user_onboarding.model.OnBoardingTheme
import br.com.edsilfer.android.user_onboarding.presenter.view.OnBoardingBottomPanel
import br.com.edsilfer.android.user_onboarding.presenter.view.OnBoardingControlPanel
import br.com.edsilfer.kotlin_support.extensions.paintStatusBar
import br.com.edsilfer.kotlin_support.extensions.putProperty


/**
 * Created by efernandes on 28/12/16.
 */
open class ActivityUserOnBoarding : AppCompatActivity(), OnBoardingBottomPanel.PanelEventListener {

    companion object {
        val PREF_USER_FIRST_TIME = "PREF_USER_FIRST_TIME"
        val ARG_ONBOARDING_THEME = "ARG_ONBOARDING_THEME"
    }

    private var mCurrentPage = 0
    private var mTheme: OnBoardingTheme? = null
    private var mViewPager: ViewPager? = null
    private var mPanelControl: OnBoardingControlPanel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_onboarding)
        instantiateMembers()
    }

    private fun instantiateMembers() {
        retrievePages()
        mViewPager = findViewById(R.id.pager) as ViewPager
        mPanelControl = OnBoardingBottomPanel(this, mTheme!!.pages.size, mTheme!!.bottomPanelColors, mTheme!!.bottomPanelLayoutResource)
        mPanelControl!!.setListener(this)
        (findViewById(R.id.divider)).setBackgroundColor(mTheme!!.bottomPanelColors.dividerColor)
        (findViewById(R.id.container) as RelativeLayout).addView(mPanelControl as ViewGroup)
        initViewPager()
    }

    private fun retrievePages() {
        try {
            mTheme = intent.extras.getSerializable(ARG_ONBOARDING_THEME) as OnBoardingTheme
        } catch (e: Exception) {
            throw IllegalArgumentException("ActivityUserOnboard requires a OnBoardingTheme object. Error Message: ${e.message}")
        }
    }

    private fun initViewPager() {
        val evaluator = ArgbEvaluator()
        mViewPager!!.adapter = SectionsPagerAdapter(supportFragmentManager, mTheme!!.pages)
        mViewPager!!.currentItem = mCurrentPage

        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                mViewPager!!.setBackgroundColor(
                        evaluator.evaluate(
                                positionOffset,
                                resources.getColor(mTheme!!.pages[position].background),
                                resources.getColor(mTheme!!.pages[if (isLastPage(position)) position else position + 1].background)
                        ) as Int
                )
                if (Build.VERSION.SDK_INT >= 21) {
                    this@ActivityUserOnBoarding.paintStatusBar(evaluator.evaluate(
                            positionOffset,
                            resources.getColor(mTheme!!.pages[position].background),
                            resources.getColor(mTheme!!.pages[if (isLastPage(position)) position else position + 1].background)
                    ) as Int, false)
                }
            }

            override fun onPageSelected(position: Int) {
                mCurrentPage = position
                mPanelControl!!.updateBar(mCurrentPage)
                mViewPager!!.setBackgroundColor(ContextCompat.getColor(baseContext, mTheme!!.pages[position].background))
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        mPanelControl!!.updateBar(mCurrentPage)
    }

    private fun isLastPage(position: Int): Boolean {
        return position == mTheme!!.pages.size - 1
    }

    // PUBLIC INTERFACE ============================================================================
    override fun onFinishedClicked() {
        finish()
        putProperty(PREF_USER_FIRST_TIME, false)
    }

    override fun onSkipClicked() {
        finish()
    }

    override fun onNextClicked() {
        mCurrentPage++
        mViewPager!!.setCurrentItem(mCurrentPage, true)
    }
}