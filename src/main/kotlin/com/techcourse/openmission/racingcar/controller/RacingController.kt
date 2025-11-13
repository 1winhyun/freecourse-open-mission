package com.techcourse.openmission.racingcar.controller

import com.techcourse.openmission.racingcar.domain.Car
import com.techcourse.openmission.racingcar.domain.RaceResult
import com.techcourse.openmission.racingcar.domain.validator.RacingCarValidator
import com.techcourse.openmission.racingcar.service.RacingGameService
import com.techcourse.openmission.racingcar.view.InputView
import com.techcourse.openmission.racingcar.view.OutputView

class RacingController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val racingGameService: RacingGameService,
    private val racingCarValidator: RacingCarValidator
) {
    fun run() {
        val names = inputView.inputCarNames()
        val cars = parseCars(names)

        val attempts = inputView.inputAttempts()
        val attempt = racingCarValidator.parseAndValidateAttempts(attempts)

        outputView.printResultStart()

        repeat(attempt) {
            racingGameService.oneRace(cars)
            outputView.printRacingRound(cars)
        }

        val winners = RaceResult.pickWinners(cars)
        outputView.printWinners(winners)
    }

    private fun parseCars(names: String?): List<Car> {
        val activeNames = names ?: throw IllegalArgumentException("이름 입력이 비어있습니다.")

        val tokens = activeNames.split(",")
        val cars = mutableListOf<Car>()

        for (token in tokens) {
            val name = token.trim()
            racingCarValidator.validateName(name)
            cars.add(Car(name))
        }

        if (cars.isEmpty()) {
            throw IllegalArgumentException("자동차는 한 대 이상이어야 합니다.")
        }

        return cars
    }
}
