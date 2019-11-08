package com.github.frayeralex.fragments.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.frayeralex.fragments.R
import com.github.frayeralex.fragments.main.interfaces.CarDataProviderInterface
import com.github.frayeralex.fragments.models.CarModel
import kotlinx.android.synthetic.main.details_view_frag.*

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
        return inflater.inflate(
            R.layout.details_view_frag,
            container, false
        ).apply { tag =
            TAG
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        details_brand.text = car?.brand
        details_year.text = car?.year.toString()
        details_model.text = car?.model
    }

    companion object {
        private val TAG = "DetailsViewFragment"
    }
}