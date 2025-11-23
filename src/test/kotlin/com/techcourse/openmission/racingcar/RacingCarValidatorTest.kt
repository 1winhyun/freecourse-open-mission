package com.techcourse.openmission.racingcar

import com.techcourse.openmission.racingcar.domain.validator.RacingCarValidator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RacingCarValidatorTest {
    private val validator = RacingCarValidator()

    @Test
    @DisplayName("자동차 이름이 null 또는 공백이면 오류가 발생한다")
    fun 이름_비어있을경우_오류() {
        val exampleOne = assertThrows(IllegalArgumentException::class.java) {
            validator.validateName(null)
        }
        val exampleTwo = assertThrows(IllegalArgumentException::class.java) {
            validator.validateName("   ")
        }

        assertEquals("자동차 이름은 공백일 수 없습니다.", exampleOne.message)
        assertEquals("자동차 이름은 공백일 수 없습니다.", exampleTwo.message)
    }

    @Test
    @DisplayName("자동차 이름이 5글자를 넘으면 오류가 발생한다")
    fun 이름_5글자_초과시_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            validator.validateName("abcdef")
        }

        assertEquals("자동차 이름은 5글자를 넘을 수 없습니다.", example.message)
    }

    @Test
    @DisplayName("시도 횟수 입력이 null이면 오류가 발생한다")
    fun 시도횟수_null일경우_오류() {
        assertThrows(IllegalArgumentException::class.java) {
            validator.parseAndValidateAttempts(null)
        }
    }

    @Test
    @DisplayName("시도 횟수 입력이 공백이면 오류가 발생한다")
    fun 시도횟수_공백시_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            validator.parseAndValidateAttempts("   ")
        }

        assertEquals("시도 횟수를 입력하지 않았습니다.", example.message)
    }

    @Test
    @DisplayName("시도 횟수 입력이 숫자가 아니면 오류가 발생한다")
    fun 시도횟수_숫자_아닐경우_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            validator.parseAndValidateAttempts("abc")
        }

        assertEquals("시도 횟수는 반드시 숫자로 입력해야합니다.", example.message)
    }

    @Test
    @DisplayName("시도 횟수가 0 이하이면 오류가 발생한다")
    fun 시도횟수_0_이하일경우_오류() {
        val exampleOne = assertThrows(IllegalArgumentException::class.java) {
            validator.parseAndValidateAttempts("0")
        }
        val exampleTwo = assertThrows(IllegalArgumentException::class.java) {
            validator.parseAndValidateAttempts("-1")
        }

        assertEquals("시도 횟수는 반드시 1 이상이어야 합니다.", exampleOne.message)
        assertEquals("시도 횟수는 반드시 1 이상이어야 합니다.", exampleTwo.message)
    }
}
