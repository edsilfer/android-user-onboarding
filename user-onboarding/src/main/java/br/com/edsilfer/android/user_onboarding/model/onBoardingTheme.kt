package br.com.edsilfer.android.user_onboarding.model

import java.io.Serializable

/**
 * Created by efernandes on 29/12/16.
 */
class OnBoardingTheme : Serializable {
    var panelColor: Int = -1 // Use this if you want default bottom bar with one color
    var pages = listOf<Page>()
    var bottomPanelColors: BottomPanelColors? = null // Create bottom bar if you need to set different colors to bottom bar elements
}