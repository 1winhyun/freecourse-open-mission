package com.techcourse.openmission.racingcar.view

import com.techcourse.openmission.racingcar.domain.constant.Messages

class RacingCarInput {
    fun inputCarNames(): String {
        print(Messages.INPUT_CAR_NAME)
        return readln()
    }

    fun inputAttempts(): String {
        print(Messages.INPUT_ATTEMPT)
        return readln()
    }
}
