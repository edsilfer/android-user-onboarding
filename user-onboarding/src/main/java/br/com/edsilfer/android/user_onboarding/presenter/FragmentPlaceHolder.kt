package br.com.edsilfer.android.user_onboarding.presenter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.edsilfer.android.user_onboarding.R

/**
 * Created by efernandes on 28/12/16.
 */

class FragmentPlaceHolder : Fragment() {

    companion object {
        private val ARG_SECTION_NUMBER = "section_number"
        private val ARG_IMAGE_RESOURCE_ID = "image_res_id"

        fun newInstance(position: Int): FragmentPlaceHolder {
            val fragment = FragmentPlaceHolder()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_pager_item, container, false)

        val label = rootView.findViewById(R.id.label) as TextView
        label.text = "?????"

        val image = rootView.findViewById(R.id.image) as ImageView
        image.setImageResource(R.drawable.img_sample)

        return rootView
    }
}