package br.com.edsilfer.android.user_onboarding.model

import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by efernandes on 28/12/16.
 */

data class Page(
        var background: Int,
        var header: Int,
        var image: Int,
        var subHeader1: Int,
        var subHeader2: Int
) : Serializable {

    override fun toString(): String {
        return Gson().toJson(this)
    }

}
