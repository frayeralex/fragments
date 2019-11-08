package com.github.frayeralex.fragments.main.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.github.frayeralex.fragments.R
import com.github.frayeralex.fragments.main.interfaces.CarDataProviderInterface
import com.github.frayeralex.fragments.main.fragments.DetailsViewFragment
import com.github.frayeralex.fragments.main.fragments.RecyclerViewFragment
import com.github.frayeralex.fragments.main.interfaces.SelectCarHandlerInterface
import com.github.frayeralex.fragments.models.CarModel
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity(),
    CarDataProviderInterface,
    SelectCarHandlerInterface {

    private var selectedCar: CarModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.content_fragment,
                    RecyclerViewFragment()
                )
                commit()
            }
        }
    }

    private fun showDetails() {
        if (resources.getBoolean(R.bool.isLand)) {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.details_fragment,
                    DetailsViewFragment()
                )
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.content_fragment,
                    DetailsViewFragment()
                )
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun getCurrentCar() = selectedCar


    override fun getCarListData(): ArrayList<CarModel> {
        val carList = arrayListOf<CarModel>()
        try {
            val inputStream: InputStream = assets.open(CAR_JSON)
            val string = inputStream.bufferedReader().use { it.readText() }

            val jsonArray = JSONArray(string)

            for (i in 0 until jsonArray.length() - 1) {
                val obj = jsonArray.getJSONObject(i)

                carList.add(
                    CarModel(
                        id = obj.getString("id"),
                        model = obj.getString("model"),
                        brand = obj.getString("brand"),
                        year = obj.getString("year").toInt()
                    )
                )
            }
        } catch (e: IOException) {
            println(e.message)
        }
        return carList
    }

    fun goBack(v: View) {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onCarSelect(car: CarModel) {
        selectedCar = car
        showDetails()
    }

    companion object {
        private const val CAR_JSON = "cars.json"
    }
}
