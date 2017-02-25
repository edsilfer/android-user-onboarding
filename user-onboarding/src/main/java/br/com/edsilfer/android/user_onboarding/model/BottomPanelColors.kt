package br.com.edsilfer.android.user_onboarding.model

import java.io.Serializable

/**
 * Colors for onboarding bottom panel
 *
 * @author Josef Hruška (josef@stepuplabs.io)
 */

class BottomPanelColors(
        var indicatorActiveColor: Int = -1,
        var indicatorInactiveColor: Int = -1,
        var skipColor: Int = -1,
        var nextColor: Int = -1,
        var finishColor: Int = -1
) : Serializable {}
