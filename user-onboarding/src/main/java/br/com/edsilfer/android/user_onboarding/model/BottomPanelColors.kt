package br.com.edsilfer.android.user_onboarding.model

import android.graphics.Color
import java.io.Serializable

/**
 * Created by Josef Hruška (josef@stepuplabs.io) on 26/2/2017.
 */

class BottomPanelColors(
        var indicatorActiveColor: Int = Color.parseColor("#FFFFFF"),
        var indicatorInactiveColor: Int = Color.parseColor("#FFFFFF"),
        var skipColor: Int = Color.parseColor("#FFFFFF"),
        var nextColor: Int = Color.parseColor("#FFFFFF"),
        var finishColor: Int = Color.parseColor("#FFFFFF"),
        var dividerColor: Int = Color.parseColor("#FFFFFF")
) : Serializable {}
