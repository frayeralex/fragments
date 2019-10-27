package com.github.frayeralex.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    var carList = arrayListOf<CarModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCarData()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.content_fragment, RecyclerViewFragment())
                commit()
            }
        }
    }

    private fun initCarData() {
        try {
            val inputStream:InputStream = assets.open(CAR_JSON)
            val string = inputStream.bufferedReader().use{it.readText()}

            val jsonArray = JSONArray(string)

            for (i in 0..(jsonArray.length() - 1)) {
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
    }

    companion object {
        private const val CAR_JSON = "cars.json"
    }
}
