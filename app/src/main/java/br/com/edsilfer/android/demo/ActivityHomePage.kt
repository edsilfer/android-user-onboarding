package br.com.edsilfer.android.demo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.edsilfer.android.user_onboarding.model.BottomPanelColors
import br.com.edsilfer.android.user_onboarding.model.OnBoardingTheme
import br.com.edsilfer.android.user_onboarding.model.Page
import br.com.edsilfer.android.user_onboarding.presenter.ActivityUserOnBoarding
import br.com.edsilfer.kotlin_support.model.xml.Text
import br.com.tyllt.view.UserOnBoardingConfiguration

/**
 * Created by efernandes on 28/12/16.
 */

class ActivityHomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, ActivityUserOnBoarding::class.java)
        intent.putExtra(ActivityUserOnBoarding.ARG_ONBOARDING_THEME, UserOnBoardingConfiguration.getConfiguration())
        startActivity(intent)
    }
}
