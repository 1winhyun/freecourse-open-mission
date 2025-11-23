package com.techcourse.openmission.guessing

import com.techcourse.openmission.guessing.contstant.Errors
import com.techcourse.openmission.guessing.domain.GameType
import com.techcourse.openmission.guessing.valitdator.GuessValidator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GuessValidatorTest {

    private val guessValidator = GuessValidator()

    @Test
    @DisplayName("1을 입력하면 숫자 게임 타입이 반환된다.")
    fun 버전_반환_숫자() {
        val result = guessValidator.parseGameType("1")

        assertEquals(GameType.NUMBER, result)
    }

    @Test
    @DisplayName("2을 입력하면 알파벳 게임 타입이 반환된다.")
    fun 버전_반환_알파벳() {
        val result = guessValidator.parseGameType("2")

        assertEquals(GameType.ALPHABET, result)
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력할 경우 오류가 발생한다.")
    fun 숫자_아닌_값_입력시_오류1() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessValidator.parseGameType("a")
        }

        assertEquals(Errors.INVALID_GAMETYPE, example.message)
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력할 경우 오류가 발생한다.")
    fun 숫자_1혹은2_아닐경우_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessValidator.parseGameType("3")
        }

        assertEquals(Errors.INVALID_VERSION, example.message)
    }

    @Test
    @DisplayName("유효한 범위의 숫자 입력 시 Int로 반환한다.")
    fun 유효한_범위_숫자_입력시_그대로_Int반환() {
        val result = guessValidator.parseAndValidateNumberGuess("50", 1, 100)

        assertEquals(50, result)
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력할 경우 오류가 발생한다.")
    fun 숫자_아닌_값_입력시_오류2() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessValidator.parseAndValidateNumberGuess("abc", 1, 100)
        }

        assertEquals(Errors.INVALID_GAMETYPE, example.message)
    }

    @Test
    @DisplayName("범위 밖의 숫자를 입력하면 오류가 발생한다.")
    fun 범위_밖의_값_입력시_오류() {
        val exampleOne = assertThrows(IllegalArgumentException::class.java) {
            guessValidator.parseAndValidateNumberGuess("0", 1, 100)
        }
        val exampleTwo = assertThrows(IllegalArgumentException::class.java) {
            guessValidator.parseAndValidateNumberGuess("101", 1, 100)
        }

        assertEquals(Errors.NUMBER_OUT_OF_RANGE, exampleOne.message)
        assertEquals(Errors.NUMBER_OUT_OF_RANGE, exampleTwo.message)
    }

    @Test
    @DisplayName("알파벳을 한글자 입력하면 그대로 Char로 반환된다.")
    fun 알파벳_한글자_입력시_Char_반환() {
        val result = guessValidator.parseAndValidateAlphabetGuess("A", 'A', 'z')

        assertEquals('A', result)
    }

    @Test
    @DisplayName("두 글자 이상 입력하면 오류가 발생한다.")
    fun 두글자_이상_입력시_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessValidator.parseAndValidateAlphabetGuess("AB", 'A', 'z')
        }

        assertEquals(Errors.INVALID_GAMETYPE, example.message)
    }

    @Test
    @DisplayName("문자가 아닌 값을 입력하면 오류가 발생한다.")
    fun 문자_아닌_값_입력시_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessValidator.parseAndValidateAlphabetGuess("1", 'A', 'z')
        }

        assertEquals(Errors.INVALID_GAMETYPE, example.message)
    }

    @Test
    @DisplayName("알파벳 범위 밖 문자 입력시 오류가 발생한다.")
    fun 알파벳_범위_밖_문자_입력시_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessValidator.parseAndValidateAlphabetGuess("가", 'A', 'z')
        }

        assertEquals(Errors.ALPHABET_OUT_OF_RANGE, example.message)
    }
}