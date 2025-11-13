package com.techcourse.openmission.racingcar.domain

object RaceResult {
    fun pickWinners(cars: List<Car>): List<String> {
        val max = bestLocation(cars)
        val raceWinners = mutableListOf<String>()

        for (car in cars) {
            if (car.location == max) {
                raceWinners.add(car.name)
            }
        }

        return raceWinners
    }

    private fun bestLocation(cars: List<Car>): Int {
        var maxPosition = 0

        for (car in cars) {
            if (car.location > maxPosition) {
                maxPosition = car.location
            }
        }

        return maxPosition
    }
}
