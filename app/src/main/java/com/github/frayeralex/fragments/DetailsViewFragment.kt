package com.github.frayeralex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailsViewFragment : Fragment() {
    private var car: CarModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val provider = activity as CarDataProviderInterface
        car = provider.getCurrentCar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.details_view_frag,
            container, false
        ).apply { tag = TAG }

        setViewData(rootView)

        return rootView
    }

    private fun setViewData(rootView: View) {
        rootView.findViewById<TextView>(R.id.details_year).text = car?.year.toString()
        rootView.findViewById<TextView>(R.id.details_brand).text = car?.brand
        rootView.findViewById<TextView>(R.id.details_model).text = car?.model
    }

    companion object {
        private val TAG = "DetailsViewFragment"
    }
}