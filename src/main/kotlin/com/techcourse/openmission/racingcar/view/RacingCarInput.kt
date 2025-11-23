package com.techcourse.openmission.racingcar.view

import com.techcourse.openmission.racingcar.domain.constant.Messages

class RacingCarInput {
    fun inputCarNames(): String {
        println(Messages.INPUT_CAR_NAME)
        return readln()
    }

    fun inputAttempts(): String {
        println(Messages.INPUT_ATTEMPT)
        return readln()
    }
}
