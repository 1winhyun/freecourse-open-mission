package com.techcourse.openmission

import com.techcourse.openmission.guessing.controller.GuessController
import com.techcourse.openmission.lotto.controller.LottoController
import com.techcourse.openmission.racingcar.controller.RacingController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

class OpenmissionApplication

fun main() {
    println("실행할 프로그램의 번호를 선택하세요.")
    println("1: Lotto")
    println("2: RacingCar")
    println("3: GuessGame")

    when (readln().trim()) {
        "1" -> {
            val lottoController = LottoController()
            lottoController.run()
        }

        "2" -> {
            val racingController = RacingController()
            racingController.run()
        }

        "3" -> {
            val guessController = GuessController()
            guessController.run()
        }

        else -> {
            println("잘못된 번호입니다. 프로그램을 종료합니다.")
        }
    }
}
