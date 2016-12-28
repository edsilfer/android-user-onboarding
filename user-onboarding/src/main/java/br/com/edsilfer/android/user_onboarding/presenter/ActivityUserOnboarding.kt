package br.com.edsilfer.android.user_onboarding.presenter

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.RelativeLayout
import br.com.edsilfer.android.user_onboarding.R
import br.com.edsilfer.android.user_onboarding.controller.SectionsPagerAdapter
import br.com.edsilfer.android.user_onboarding.model.OnBoardingBottomPanel
import br.com.edsilfer.android.user_onboarding.model.Page
import br.com.edsilfer.kotlin_support.extensions.putProperty
import java.util.*


/**
 * Created by efernandes on 28/12/16.
 */
class ActivityUserOnBoarding : AppCompatActivity(), OnBoardingBottomPanel.PanelEventListener {

    companion object {
        val PREF_USER_FIRST_TIME = "PREF_USER_FIRST_TIME"
        val ARG_ONBOARDING_PAGES = "ARG_ONBOARDING_PAGES"
    }

    private var mCurrentPage = 0
    private var mPages = arrayListOf<Page>()
    private var mViewPager: ViewPager? = null
    private var mPanelControl: OnBoardingBottomPanel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_onboarding)
        instantiateMembers()
    }

    private fun instantiateMembers() {
        retrievePages()
        mViewPager = findViewById(R.id.pager) as ViewPager
        mPanelControl = OnBoardingBottomPanel(this, mPages.size)
        mPanelControl!!.setListener(this)
        (findViewById(R.id.container) as RelativeLayout).addView(mPanelControl)
        initViewPager()
    }

    private fun retrievePages() {
        try {
            mPages = intent.extras.getSerializable(ARG_ONBOARDING_PAGES) as ArrayList<Page>
        } catch (e: Exception) {
            throw IllegalArgumentException("ActivityUserOnboard requires an ArrayList<Page> to work")
        }
    }

    private fun initViewPager() {
        val evaluator = ArgbEvaluator()
        mViewPager!!.adapter = SectionsPagerAdapter(supportFragmentManager, mPages)
        mViewPager!!.currentItem = mCurrentPage

        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                mViewPager!!.setBackgroundColor(
                        evaluator.evaluate(
                                positionOffset,
                                resources.getColor(mPages[position].background),
                                resources.getColor(mPages[if (isLastPage(position)) position else position + 1].background)
                        ) as Int
                )
            }

            override fun onPageSelected(position: Int) {
                mCurrentPage = position
                mPanelControl!!.updateBar(mCurrentPage)
                mViewPager!!.setBackgroundColor(mPages[position].background)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        mPanelControl!!.updateBar(mCurrentPage)
    }

    private fun isLastPage(position: Int): Boolean {
        return position == mPages.size - 1
    }

    // PUBLIC INTERFACE
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