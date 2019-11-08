package com.github.frayeralex.fragments.main.interfaces

import com.github.frayeralex.fragments.models.CarModel

interface CarDataProviderInterface {
    fun getCarListData(): ArrayList<CarModel>
    fun getCurrentCar(): CarModel?
}