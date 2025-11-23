package com.techcourse.openmission.racingcar

import com.techcourse.openmission.racingcar.domain.Car
import com.techcourse.openmission.racingcar.domain.RaceResult
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RaceResultTest {
    @Test
    @DisplayName("가장 멀리 간 자동차 한 대만 우승자인 경우")
    fun 우승자_한대() {
        val cars = listOf(
            Car("han", location = 3),
            Car("bae", location = 5),
            Car("kim", location = 1),
        )

        val winners = RaceResult.pickWinners(cars)

        assertEquals(listOf("bae"), winners)
    }

    @Test
    @DisplayName("가장 멀리 간 자동차가 여러 대면 공동 우승이다")
    fun 우승자_여러대() {
        val cars = listOf(
            Car("han", location = 5),
            Car("kim", location = 3),
            Car("bae", location = 5),
        )

        val winners = RaceResult.pickWinners(cars)

        assertEquals(listOf("han", "bae"), winners)
    }
}
