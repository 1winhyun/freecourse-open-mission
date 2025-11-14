package com.techcourse.openmission.racingcar.service

import com.techcourse.openmission.racingcar.domain.Car
import com.techcourse.openmission.racingcar.domain.rule.DefaultMoveRule
import com.techcourse.openmission.racingcar.domain.rule.MoveRule

class RacingGameService(
    private val numberGenerator: NumberGenerator = RandomNumberGenerator(),
    private val moveRule: MoveRule = DefaultMoveRule()
) {
    fun oneRace(cars: List<Car>) {
        for (car in cars) {
            val randomNumber = numberGenerator.randomNumber(0, 9)
            car.moveIfAllowed(moveRule, randomNumber)
        }
    }
}
