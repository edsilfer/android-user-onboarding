package br.com.edsilfer.android.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.edsilfer.android.user_onboarding.model.Page
import br.com.edsilfer.android.user_onboarding.presenter.ActivityUserOnBoarding

/**
 * Created by efernandes on 28/12/16.
 */

class ActivityHomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pages = arrayListOf<Page>()
        pages.add(getPage1())
        pages.add(getPage2())
        pages.add(getPage3())


        val intent = Intent(this, ActivityUserOnBoarding::class.java)
        intent.putExtra(ActivityUserOnBoarding.ARG_ONBOARDING_PAGES, pages)
        startActivity(intent)

    }

    private fun getPage3(): Page {
        val page3 = Page(
                R.color.clr_custom_background_page_03,
                R.string.str_header_03,
                R.drawable.img_sample_01,
                R.string.str_subheader1_03,
                R.string.str_subheader2_03
        )
        return page3
    }

    private fun getPage2(): Page {
        val page2 = Page(
                R.color.clr_custom_background_page_02,
                R.string.str_header_02,
                R.drawable.img_sample_02,
                R.string.str_subheader1_02,
                R.string.str_subheader2_02
        )
        return page2
    }

    private fun getPage1(): Page {
        val page1 = Page(
                R.color.clr_custom_background_page_01,
                R.string.str_header_01,
                R.drawable.img_sample_01,
                R.string.str_subheader1_01,
                R.string.str_subheader2_01
        )
        return page1
    }
}
