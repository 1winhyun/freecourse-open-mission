package com.techcourse.openmission.racingcar

import com.techcourse.openmission.racingcar.domain.Car
import com.techcourse.openmission.racingcar.domain.rule.DefaultMoveRule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MoveRuleTest {
    @Test
    @DisplayName("DefaultMoveRule에서 랜덤 값이 4 이상이면 자동차는 전진한다")
    fun 랜덤값_4이상_전진() {
        val car = Car("han", location = 0)
        val moveRule = DefaultMoveRule()

        car.moveIfAllowed(moveRule, 4)
        assertEquals(1, car.location)

        car.moveIfAllowed(moveRule, 9)
        assertEquals(2, car.location)
    }

    @Test
    @DisplayName("DefaultMoveRule에서 랜덤 값이 4 미만이면 자동차는 전진하지 않는다")
    fun 랜덤값_4미만_정지() {
        val car = Car("bae", location = 0)
        val moveRule = DefaultMoveRule()

        car.moveIfAllowed(moveRule, 3)
        car.moveIfAllowed(moveRule, 0)

        assertEquals(0, car.location)
    }
}