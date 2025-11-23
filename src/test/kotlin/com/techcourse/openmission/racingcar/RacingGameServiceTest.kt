package com.techcourse.openmission.racingcar

import com.techcourse.openmission.racingcar.domain.Car
import com.techcourse.openmission.racingcar.domain.rule.DefaultMoveRule
import com.techcourse.openmission.racingcar.service.NumberGenerator
import com.techcourse.openmission.racingcar.service.RacingGameService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RacingGameServiceTest {
    private class FixedNumberGenerator(private val fixed: Int) : NumberGenerator {
        override fun randomNumber(min: Int, max: Int): Int = fixed
    }

    @Test
    @DisplayName("랜덤 값이 4 이상이면 모든 자동차가 한 칸씩 이동한다")
    fun 값_4이상_모든_자동차_이동() {
        val cars = listOf(
            Car("pobi"),
            Car("woni"),
            Car("jun"),
        )

        val generator = FixedNumberGenerator(4)
        val service = RacingGameService(
            numberGenerator = generator,
            moveRule = DefaultMoveRule()
        )

        service.oneRace(cars)

        cars.forEach { car ->
            assertEquals(1, car.location)
        }
    }

    @Test
    @DisplayName("랜덤 값이 4 미만이면 어떤 자동차도 이동하지 않는다")
    fun 값_4미만_모든_자동차_정지() {
        val cars = listOf(
            Car("pobi"),
            Car("woni"),
            Car("jun"),
        )

        val generator = FixedNumberGenerator(3)
        val service = RacingGameService(
            numberGenerator = generator,
            moveRule = DefaultMoveRule()
        )

        service.oneRace(cars)

        cars.forEach { car ->
            assertEquals(0, car.location)
        }
    }

    @Test
    @DisplayName("랜덤 값이 섞여 있으면 일부 자동차만 이동한다")
    fun 일부만_이동() {
        val cars = listOf(
            Car("han"),
            Car("bae"),
            Car("kim"),
        )

        val generator = object : NumberGenerator {
            private val values = listOf(3, 4, 9)
            private var index = 0

            override fun randomNumber(min: Int, max: Int): Int {
                val value = values[index]
                index = (index + 1) % values.size
                return value
            }
        }

        val service = RacingGameService(
            numberGenerator = generator,
            moveRule = DefaultMoveRule()
        )

        service.oneRace(cars)

        assertEquals(0, cars[0].location)
        assertEquals(1, cars[1].location)
        assertEquals(1, cars[2].location)
    }
}
