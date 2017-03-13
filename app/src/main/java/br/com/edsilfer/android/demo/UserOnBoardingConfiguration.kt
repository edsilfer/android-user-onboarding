package br.com.tyllt.view

import android.graphics.Color
import br.com.edsilfer.android.demo.R
import br.com.edsilfer.android.user_onboarding.model.BottomPanelColors
import br.com.edsilfer.android.user_onboarding.model.OnBoardingTheme
import br.com.edsilfer.android.user_onboarding.model.Page
import br.com.edsilfer.kotlin_support.model.xml.Text

/**
 * Created by efernandes on 30/12/2016.
 */

object UserOnBoardingConfiguration {

    fun getConfiguration(): OnBoardingTheme {
        val pages = arrayListOf<Page>()
        pages.add(getPage1())
        pages.add(getPage2())
        pages.add(getPage3())
        pages.add(getPage4())
        pages.add(getPage5())

        val theme = OnBoardingTheme()
        theme.pages = pages
        theme.bottomPanelColors = getBottomPanelColors()
        theme.bottomPanelLayoutResource = getBottomPanelLayoutResource()

        return theme
    }

    private fun getPage5(): Page {
        val page = Page(
                R.color.clr_custom_background_page_05,
                R.string.str_act_user_onboarding_header_page_05,
                getHeaderStyle(),
                R.drawable.img_usr_onboard_page_05,
                R.string.str_act_user_onboarding_subheader1_page_05,
                getSubHeader1Style(),
                R.string.str_act_user_onboarding_subheader2_page_05,
                getSubHeader2Style()
        )
        return page
    }

    private fun getPage4(): Page {
        val page = Page(
                R.color.clr_custom_background_page_04,
                R.string.str_act_user_onboarding_header_page_04,
                getHeaderStyle(),
                R.drawable.img_usr_onboard_page_03,
                R.string.str_act_user_onboarding_subheader1_page_04,
                getSubHeader1Style(),
                R.string.str_act_user_onboarding_subheader2_page_04,
                getSubHeader2Style()
        )
        return page
    }

    private fun getPage3(): Page {
        val page = Page(
                R.color.clr_custom_background_page_03,
                R.string.str_act_user_onboarding_header_page_03,
                getHeaderStyle(),
                R.drawable.img_usr_onboard_page_03,
                R.string.str_act_user_onboarding_subheader1_page_03,
                getSubHeader1Style(),
                R.string.str_act_user_onboarding_subheader2_page_03,
                getSubHeader2Style()
        )
        return page
    }

    private fun getPage2(): Page {
        val page = Page(
                R.color.clr_custom_background_page_02,
                R.string.str_act_user_onboarding_header_page_02,
                getHeaderStyle(),
                R.drawable.img_usr_onboard_page_02,
                R.string.str_act_user_onboarding_subheader1_page_02,
                getSubHeader1Style(),
                R.string.str_act_user_onboarding_subheader2_page_02,
                getSubHeader2Style()
        )
        return page
    }

    private fun getPage1(): Page {
        val page = Page(
                R.color.clr_custom_background_page_01,
                R.string.str_act_user_onboarding_header_page_01,
                getHeaderStyle(),
                R.drawable.img_usr_onboard_page_01,
                R.string.str_act_user_onboarding_subheader1_page_01,
                getSubHeader1Style(),
                R.string.str_act_user_onboarding_subheader2_page_01,
                getSubHeader2Style()
        )
        return page
    }

    private fun getHeaderStyle(): Text {
        val headerStyle = Text()
        headerStyle.color = "#FFFFFF"
        headerStyle.font = "sans-serif-black"
        headerStyle.size = 32f
        headerStyle.style = "bold"
        return headerStyle
    }

    private fun getSubHeader1Style(): Text {
        val headerStyle = Text()
        headerStyle.color = "#FFFFFF"
        headerStyle.font = "sans-serif"
        headerStyle.size = 28f
        headerStyle.style = "bold"
        return headerStyle
    }

    private fun getSubHeader2Style(): Text {
        val headerStyle = Text()
        headerStyle.color = "#FFFFFF"
        headerStyle.font = "sans-serif-thin"
        headerStyle.size = 18f
        headerStyle.style = "italic"
        return headerStyle
    }

    private fun getBottomPanelColors(): BottomPanelColors {
        val colors = BottomPanelColors()
        colors.finishColor = Color.parseColor("#FFFFFF")
        colors.nextColor = Color.parseColor("#FFFFFF")
        colors.indicatorActiveColor = Color.parseColor("#FFFFFF")
        colors.indicatorInactiveColor = Color.parseColor("#FFFFFF")
        colors.skipColor = Color.parseColor("#FFFFFF")
        return colors
    }

    private fun getBottomPanelLayoutResource(): Int {
        return R.layout.util_bottom_toolbar // You can set your custom bottom bar layout here.
    }
}
