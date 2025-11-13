package com.techcourse.openmission.racingcar.view

import com.techcourse.openmission.racingcar.domain.Car

class OutputView {
    fun printResultStart() {
        println()
        println("실행결과")
    }

    fun printRacingRound(cars: List<Car>) {
        for (car in cars) {
            println("${car.name}: ${"-".repeat(car.location)}")
        }
        println()
    }

    fun printWinners(winners: List<String>) {
        println("최종 우승자: ${winners.joinToString(", ")}")
    }
}
