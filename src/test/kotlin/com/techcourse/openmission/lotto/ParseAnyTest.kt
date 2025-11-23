package com.techcourse.openmission.lotto

import com.techcourse.openmission.lotto.domain.constant.Errors
import com.techcourse.openmission.lotto.util.ParseAny
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ParseAnyTest {

    @Test
    @DisplayName("정상적인 숫자 문자열은 Int로 변환된다.")
    fun 정상_숫자_문자열_변환() {
        val result = ParseAny.parseInt("123")

        assertEquals(123, result)
    }

    @Test
    @DisplayName("숫자가 아닌 문자열은 오류가 발생한다.")
    fun 숫자_아닌_문자열_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            ParseAny.parseInt("abc")
        }

        assertEquals(Errors.REQUIRE_NUMBER, example.message)
    }

    @Test
    @DisplayName("당첨 번호 문자열을 파싱해 리스트로 변환한다.")
    fun 당첨_번호_문자열_리스트_변환() {
        val result = ParseAny.parseWinningNumbers("1, 2, 3,4,5,6")

        assertEquals(listOf(1, 2, 3, 4, 5, 6), result)
    }

    @Test
    @DisplayName("당첨번호가 null 또는 빈 값이면 오류가 발생한다.")
    fun 당첨번호_비었으면_오류() {
        val exampleOne = assertThrows(IllegalArgumentException::class.java) {
            ParseAny.parseWinningNumbers(null)
        }
        val exampleTwo = assertThrows(IllegalArgumentException::class.java) {
            ParseAny.parseWinningNumbers("")
        }

        assertEquals(Errors.WINNING_NUMBER_NULL, exampleOne.message)
        assertEquals(Errors.WINNING_NUMBER_NULL, exampleTwo.message)
    }

    @Test
    @DisplayName("당첨 번호에 숫자가 아닌 값이 포함되면 오류가 발생한다.")
    fun 당첨번호_숫자_아니면_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            ParseAny.parseWinningNumbers("1, 2, a, 4")
        }

        assertEquals(Errors.REQUIRE_NUMBER, example.message)
    }

    @Test
    @DisplayName("보너스 번호 문자열을 파싱해 변환한다.")
    fun 보너스_번호_문자열_변환() {
        val result = ParseAny.parseBonusNumber("7")

        assertEquals(7, result)
    }

    @Test
    @DisplayName("보너스 번호가 여러개면 오류가 발생한다.")
    fun 보너스_번호_여러개면_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            ParseAny.parseBonusNumber("1,2")
        }

        assertEquals(Errors.BONUS_NUMBER_SINGLE, example.message)
    }

    @Test
    @DisplayName("보너스 번호가 숫자가 아니면 오류가 발생한다.")
    fun 보너스_번호_숫자_아니면_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            ParseAny.parseBonusNumber("a")
        }

        assertEquals(Errors.REQUIRE_NUMBER, example.message)
    }
}