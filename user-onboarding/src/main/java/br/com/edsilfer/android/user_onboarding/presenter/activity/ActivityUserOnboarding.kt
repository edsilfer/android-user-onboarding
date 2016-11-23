package br.com.edsilfer.android.user_onboarding.presenter.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.edsilfer.android.user_onboarding.R
import br.com.edsilfer.kotlin_support.extensions.paintStatusBar

/**
 * Created by efernandes on 23/11/16.
 */

class ActivityUserOnboarding : AppCompatActivity(), ViewPager.OnPageChangeListener {
    private var mLayer: View? = null
    private var mBegin: Button? = null
    private var mIndicator: LinearLayout? = null

    private var mDots: Array<ImageView>? = null
    private val mImageResources = intArrayOf()

    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_onboarding)

        findResources()
        addStartAppListener()
        initPageIndicator()
    }

    private fun findResources() {
        mBegin = findViewById(R.id.begin) as Button
        mLayer = findViewById(R.id.bk_layer)
        val pager = findViewById(R.id.pager) as ViewPager
        pager.adapter = UserOnboardingAdapter(this, mImageResources)
        pager.currentItem = 0
        pager.addOnPageChangeListener(this)
        mIndicator = findViewById(R.id.indicator) as LinearLayout
    }

    private fun addStartAppListener() {
        mBegin!!.setOnClickListener { view ->
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            UserSettings.instance().setFirstUseFalse()
            finish()
        }
    }

    private fun initPageIndicator() {
        mDots = arrayOfNulls<ImageView>(mImageResources.size)

        for (i in mImageResources.indices) {
            mDots[i] = ImageView(this)
            mDots!![i].setImageDrawable(getResources().getDrawable(R.drawable.rsc_indicator_deactivated))

            val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(4, 0, 4, 0)
            mIndicator!!.addView(mDots!![i], params)
        }

        mDots!![0].setImageDrawable(getResources().getDrawable(R.drawable.rsc_indicator_activated))
    }

    // VIEW PAGER CALLBACK
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        updateAlphaResources(position, positionOffset)
    }

    override fun onPageSelected(position: Int) {
        mLayer!!.alpha = position.toFloat()
        mBegin!!.alpha = position.toFloat()
        updatePageIndicator(position)
        setStatusBarColor(position)
    }

    private fun setStatusBarColor(position: Int) {
        when (position) {
            0 -> {
                paintStatusBar(R.color.clr_user_onboard_page_1)
            }
            1 -> {
                paintStatusBar(R.color.clr_user_onboard_page_2)
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
        // DO NOTHING
    }

    private fun updateAlphaResources(position: Int, positionOffset: Float) {
        if (position == 0) {
            mLayer!!.alpha = positionOffset
            mBegin!!.alpha = positionOffset
        } else {
            mLayer!!.alpha = 1 - positionOffset
            mBegin!!.alpha = 1 - positionOffset
        }
    }

    private fun updatePageIndicator(position: Int) {
        val dot = mDots!![position]
        for (i in mImageResources.indices) {
            mDots!![i].setImageDrawable(getResources().getDrawable(R.drawable.rsc_indicator_deactivated))
        }
        dot.setImageDrawable(getResources().getDrawable(R.drawable.rsc_indicator_activated))
    }
}
