package br.com.edsilfer.android.user_onboarding.model

import br.com.edsilfer.android.user_onboarding.R
import java.io.Serializable

/**
 * Created by efernandes on 29/12/16.
 */
class OnBoardingTheme : Serializable {
    var pages = listOf<Page>()
    var bottomPanelColors: BottomPanelColors = BottomPanelColors()
    var bottomPanelLayoutResource: Int = R.layout.util_bottom_toolbar
}