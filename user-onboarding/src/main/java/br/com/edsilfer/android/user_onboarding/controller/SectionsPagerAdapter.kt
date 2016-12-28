package br.com.edsilfer.android.user_onboarding.controller

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.edsilfer.android.user_onboarding.model.Page

import br.com.edsilfer.android.user_onboarding.presenter.FragmentPlaceHolder

/**
 * Created by efernandes on 28/12/16.
 */

class SectionsPagerAdapter(fragmentManager: FragmentManager, val mPages: List<Page>) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return FragmentPlaceHolder.newInstance(mPages[position])
    }

    override fun getCount(): Int {
        return mPages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "SECTION $position"
    }
}
