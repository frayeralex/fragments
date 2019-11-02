package com.github.frayeralex.fragments

interface CarDataProviderInterface {
    fun getCarListData(): ArrayList<CarModel>
    fun getCurrentCar(): CarModel?
}