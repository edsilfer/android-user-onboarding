package br.com.edsilfer.android.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.edsilfer.android.user_onboarding.model.OnBoardingTheme
import br.com.edsilfer.android.user_onboarding.model.Page
import br.com.edsilfer.android.user_onboarding.presenter.ActivityUserOnBoarding
import br.com.edsilfer.kotlin_support.model.xml.Text

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
        pages.add(getPage4())

        val theme = OnBoardingTheme()
        theme.pages = pages
        theme.panelColor = android.R.color.white

        val intent = Intent(this, ActivityUserOnBoarding::class.java)
        intent.putExtra(ActivityUserOnBoarding.ARG_ONBOARDING_THEME, theme)
        startActivity(intent)
    }

    private fun getPage4(): Page {
        val page3 = Page(
                R.color.clr_custom_background_page_04,
                R.string.str_header_04,
                getHeaderStyle(),
                R.drawable.img_sample_04,
                R.string.str_subheader1_04,
                getSubHeader1Style(),
                R.string.str_subheader2_04,
                getSubHeader2Style()
        )
        return page3
    }

    private fun getPage3(): Page {
        val page3 = Page(
                R.color.clr_custom_background_page_03,
                R.string.str_header_03,
                getHeaderStyle(),
                R.drawable.img_sample_03,
                R.string.str_subheader1_03,
                getSubHeader1Style(),
                R.string.str_subheader2_03,
                getSubHeader2Style()
        )
        return page3
    }

    private fun getPage2(): Page {
        val page2 = Page(
                R.color.clr_custom_background_page_02,
                R.string.str_header_02,
                getHeaderStyle(),
                R.drawable.img_sample_02,
                R.string.str_subheader1_02,
                getSubHeader1Style(),
                R.string.str_subheader2_02,
                getSubHeader2Style()
        )
        return page2
    }

    private fun getPage1(): Page {
        getHeaderStyle()

        val page1 = Page(
                R.color.clr_custom_background_page_01,
                R.string.str_header_01,
                getHeaderStyle(),
                R.drawable.img_sample_01,
                R.string.str_subheader1_01,
                getSubHeader1Style(),
                R.string.str_subheader2_01,
                getSubHeader2Style()
        )
        return page1
    }

    private fun getHeaderStyle(): Text {
        val headerStyle = Text()
        headerStyle.color = "#FFFFFF"
        headerStyle.font = "sans-serif-black"
        headerStyle.size = 42f
        headerStyle.style = "bold"
        return headerStyle
    }

    private fun getSubHeader1Style(): Text {
        val headerStyle = Text()
        headerStyle.color = "#FFFFFF"
        headerStyle.font = "sans-serif"
        headerStyle.size = 24f
        headerStyle.style = "bold"
        return headerStyle
    }

    private fun getSubHeader2Style(): Text {
        val headerStyle = Text()
        headerStyle.color = "#FFFFFF"
        headerStyle.font = "sans-serif-thin"
        headerStyle.size = 15f
        headerStyle.style = "italic"
        return headerStyle
    }
}
