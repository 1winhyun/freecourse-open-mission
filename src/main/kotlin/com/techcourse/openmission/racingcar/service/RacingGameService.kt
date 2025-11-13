package com.techcourse.openmission.racingcar.service

import com.techcourse.openmission.racingcar.domain.Car
import com.techcourse.openmission.racingcar.domain.rule.MoveRule

class RacingGameService(
    private val numberGenerator: NumberGenerator,
    private val moveRule: MoveRule
) {
    fun oneRace(cars: List<Car>) {
        for (car in cars) {
            val randomNumber = numberGenerator.randomNumber(0, 9)
            car.moveIfAllowed(moveRule, randomNumber)
        }
    }
}