package br.com.edsilfer.android.user_onboarding.presenter

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.edsilfer.android.user_onboarding.R
import br.com.edsilfer.android.user_onboarding.model.Page
import br.com.edsilfer.kotlin_support.extensions.setStyle

/**
 * Created by efernandes on 28/12/16.
 */

class FragmentPlaceHolder : Fragment() {

    companion object {
        private val ARG_PAGE = "ARG_PAGE"

        fun newInstance(page: Page): FragmentPlaceHolder {
            val fragment = FragmentPlaceHolder()
            val args = Bundle()
            args.putSerializable(ARG_PAGE, page)
            fragment.arguments = args
            return fragment
        }
    }

    // This is necessary to change layout to portrait/landscape mode. OnCreateView is not called after rotation.
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        val viewGroup = view as ViewGroup?
        viewGroup!!.removeAllViewsInLayout()
        val view = onCreateView(activity.layoutInflater, viewGroup, null)
        viewGroup.addView(view)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val page = arguments.getSerializable(ARG_PAGE) as Page
        val rootView = inflater!!.inflate(page.contentLayoutResource, container, false)

        val header = rootView.findViewById(R.id.header) as TextView
        val image = rootView.findViewById(R.id.image) as ImageView
        val subHeader1 = rootView.findViewById(R.id.subheader1) as TextView
        val subHeader2 = rootView.findViewById(R.id.subheader2) as TextView

        header.setStyle(page.headerStyle)
        subHeader1.setStyle(page.subHeader1Style)
        subHeader2.setStyle(page.subHeader2Style)

        header.text = context.getString(page.header)
        subHeader1.text = context.getString(page.subHeader1)
        subHeader2.text = context.getString(page.subHeader2)

        image.setImageResource(page.image)

        return rootView
    }
}