package com.techcourse.openmission.lotto

import com.techcourse.openmission.lotto.domain.constant.Errors
import com.techcourse.openmission.lotto.validator.LottoValidator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoValidatorTest {

    @Test
    @DisplayName("로또 번호가 6개가 아니면 오류가 발생한다.")
    fun 로또_번호_6개_아닐시_오류() {
        val numbers = listOf(1, 2, 3, 4, 5)

        val example = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.validateLottoNumbers(numbers)
        }

        assertEquals(Errors.NUMBER_SIZE, example.message)
    }

    @Test
    @DisplayName("로또 번호가 범위를 벗어나면 오류가 발생한다.")
    fun 로또_번호_범위밖이면_오류() {
        val numbers = listOf(0, 1, 2, 3, 4, 5)

        val example = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.validateLottoNumbers(numbers)
        }

        assertEquals(Errors.NUMBER_RANGE, example.message)
    }

    @Test
    @DisplayName("로또 번호에 중복이 있으면 오류가 발생한다.")
    fun 로또_번호_중복시_오류() {
        val numbers = listOf(1, 1, 2, 3, 4, 5)

        val example = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.validateLottoNumbers(numbers)
        }

        assertEquals(Errors.NUMBER_DUPLICATE, example.message)
    }

    @Test
    @DisplayName("구입금액이 최소금액보다 작으면 오류가 발생한다.")
    fun 구입금액_최소금액보다_작으면_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.validateMoney(500)
        }

        assertEquals(Errors.MONEY_MIN, example.message)
    }

    @Test
    @DisplayName("구입금액이 1000원 단위가 아닐 시 오류가 발생한다.")
    fun 구입금액_1000단위_아닐시_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.validateMoney(1500)
        }

        assertEquals(Errors.MONEY_UNIT, example.message)
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아닐 경우 오류가 발생한다.")
    fun 당첨번호_6개_아닐시_오류() {
        val numbers = listOf(1, 2, 3, 4, 5)

        val example = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.validateWinningNumbers(numbers)
        }

        assertEquals(Errors.WINNING_NUMBER_SIZE, example.message)
    }

    @Test
    @DisplayName("당첨 번호 범위를 벗어나면 오류가 발생한다.")
    fun 당첨번호_범위_벗어날시_오류() {
        val numbers = listOf(0, 1, 2, 3, 4, 5)

        val example = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.validateWinningNumbers(numbers)
        }

        assertEquals(Errors.WINNING_BONUS_NUMBER_RANGE, example.message)
    }

    @Test
    @DisplayName("당첨 번호에 중복이 있으면 오류가 발생한다.")
    fun 당첨번호_중복시_오류() {
        val numbers = listOf(1, 2, 3, 4, 5, 5)

        val example = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.validateWinningNumbers(numbers)
        }

        assertEquals(Errors.WINNING_NUMBER_DUPLICATE, example.message)
    }

    @Test
    @DisplayName("보너스번호와 당첨 번호가 중복되면 오류가 발생한다.")
    fun 보너스번호_당첨번호_중복시_오류() {
        val winningNumbers = setOf(1, 2, 3, 4, 5, 6)
        val bonus = 6

        val example = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.validateWinningAndBonusNumbers(winningNumbers, bonus)
        }

        assertEquals(Errors.WINNING_BONUS_DUPLICATE, example.message)
    }

    @Test
    @DisplayName("보너스 번호가 범위를 벗어나면 오류가 발생한다.")
    fun 보너스번호_범위_벗어날시_오류() {
        val winningNumbers = setOf(1, 2, 3, 4, 5, 6)
        val bonus = 0

        val example = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.validateWinningAndBonusNumbers(winningNumbers, bonus)
        }

        assertEquals(Errors.WINNING_BONUS_NUMBER_RANGE, example.message)
    }

    @Test
    @DisplayName("당첨 번호 입력이 비어있으면 오류가 발생한다.")
    fun 당첨번호_입력이_비어있으면_오류() {
        val exampleOne = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.ifWinningNumbersNull(null)
        }
        val exampleTwo = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.ifWinningNumbersNull("")
        }

        assertEquals(Errors.WINNING_NUMBER_NULL, exampleOne.message)
        assertEquals(Errors.WINNING_NUMBER_NULL, exampleTwo.message)
    }

    @Test
    @DisplayName("보너스 번호가 비어있으면 오류가 발생한다.")
    fun 보너스번호_비어있으면_오류() {
        val exampleOne = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.ifBonusNumberSingle(null)
        }
        val exampleTwo = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.ifBonusNumberSingle("")
        }

        assertEquals(Errors.BONUS_NUMBER_NULL, exampleOne.message)
        assertEquals(Errors.BONUS_NUMBER_NULL, exampleTwo.message)
    }

    @Test
    @DisplayName("보너스 번호가 여러개면 오류가 발생한다.")
    fun 보너스번호_여러개일_경우_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            LottoValidator.ifBonusNumberSingle("1,2")
        }

        assertEquals(Errors.BONUS_NUMBER_SINGLE, example.message)
    }
}
