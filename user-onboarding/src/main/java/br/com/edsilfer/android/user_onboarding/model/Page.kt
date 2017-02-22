package br.com.edsilfer.android.user_onboarding.model

import br.com.edsilfer.android.user_onboarding.R
import br.com.edsilfer.kotlin_support.model.xml.Text
import java.io.Serializable

/**
 * Created by efernandes on 28/12/16.
 */

data class Page(
        var background: Int,
        var header: Int,
        var headerStyle: Text,
        var image: Int,
        var subHeader1: Int,
        var subHeader1Style: Text,
        var subHeader2: Int,
        var subHeader2Style: Text,
        // You can set your custom layout here. Otherwise, the default layout will be used.
        var contentLayoutResource: Int = R.layout.fragment_pager_item
) : Serializable {

}
