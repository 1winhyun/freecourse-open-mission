package com.techcourse.openmission.racingcar.controller

import com.techcourse.openmission.racingcar.domain.Car
import com.techcourse.openmission.racingcar.domain.RaceResult
import com.techcourse.openmission.racingcar.domain.constant.Errors
import com.techcourse.openmission.racingcar.domain.validator.RacingCarValidator
import com.techcourse.openmission.racingcar.service.RacingGameService
import com.techcourse.openmission.racingcar.view.RacingCarInput
import com.techcourse.openmission.racingcar.view.RacingCarOutput

class RacingController(
    private val racingCarInput: RacingCarInput = RacingCarInput(),
    private val racingCarOutput: RacingCarOutput = RacingCarOutput(),
    private val racingGameService: RacingGameService = RacingGameService(),
    private val racingCarValidator: RacingCarValidator = RacingCarValidator(),
) {
    fun run() {
        val cars = readCars()
        val attempts = readAttempts()

        racingCarOutput.printResultStart()

        repeat(attempts) {
            racingGameService.oneRace(cars)
            racingCarOutput.printRacingRound(cars)
        }

        val winners = RaceResult.pickWinners(cars)
        racingCarOutput.printWinners(winners)
    }

    private fun readCars(): List<Car> {
        while (true) {
            try {
                val names = racingCarInput.inputCarNames()
                return parseCars(names)
            } catch (e: IllegalArgumentException) {
                racingCarOutput.printError(e.message)
            }
        }
    }

    private fun readAttempts(): Int {
        while (true) {
            try {
                val attempts = racingCarInput.inputAttempts()
                return racingCarValidator.parseAndValidateAttempts(attempts)
            } catch (e: IllegalArgumentException) {
                racingCarOutput.printError(e.message)
            }
        }
    }

    private fun parseCars(names: String?): List<Car> {
        val activeNames = names ?: throw IllegalArgumentException(Errors.NAME_NULL)

        val tokens = activeNames.split(",")
        val cars = mutableListOf<Car>()

        for (token in tokens) {
            val name = token.trim()
            racingCarValidator.validateName(name)
            cars.add(Car(name))
        }

        if (cars.isEmpty()) {
            throw IllegalArgumentException(Errors.CAR_LESS_ONE)
        }

        return cars
    }
}
