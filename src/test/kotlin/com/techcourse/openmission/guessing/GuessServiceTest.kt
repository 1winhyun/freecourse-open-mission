package com.techcourse.openmission.guessing

import com.techcourse.openmission.guessing.contstant.Errors
import com.techcourse.openmission.guessing.domain.GameType
import com.techcourse.openmission.guessing.domain.GuessResult
import com.techcourse.openmission.guessing.service.GuessService
import com.techcourse.openmission.guessing.service.RandomGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GuessServiceTest {
    private class FixedRandomGenerator(
        private val number: Int = 50,
        private val alphabet: Char = 'M'
    ) : RandomGenerator {
        override fun generateRandomNumber(min: Int, max: Int): Int = number
        override fun generateRandomAlphabet(): Char = alphabet
    }

    private val guessService = GuessService(FixedRandomGenerator())

    @Test
    @DisplayName("1을 입력하면 숫자 게임 타입이 반환된다.")
    fun 버전_반환_숫자() {
        val result = guessService.parseGameType("1")

        assertEquals(GameType.NUMBER, result)
    }

    @Test
    @DisplayName("2을 입력하면 알파벳 게임 타입이 반환된다.")
    fun 버전_반환_알파벳() {
        val result = guessService.parseGameType("2")

        assertEquals(GameType.ALPHABET, result)
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력할 경우 오류가 발생한다.")
    fun 숫자_아닌_값_입력시_오류1() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessService.parseGameType("a")
        }

        assertEquals(Errors.INVALID_GAMETYPE, example.message)
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력할 경우 오류가 발생한다.")
    fun 숫자_1혹은2_아닐경우_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessService.parseGameType("3")
        }

        assertEquals(Errors.INVALID_VERSION, example.message)
    }

    @Test
    @DisplayName("랜덤 숫자 생성은 RandomGenerator에 위임한다.")
    fun 랜덤_숫자_생성은_RandomGenerator가_수행() {
        val fixedService = GuessService(FixedRandomGenerator(number = 77))

        val answer = fixedService.generateNumberAnswer()

        assertEquals(77, answer)
    }

    @Test
    @DisplayName("유효한 범위의 숫자 입력 시 Int로 반환한다.")
    fun 유효한_범위_숫자_입력시_그대로_Int반환() {
        val result = guessService.parseAndValidateNumberGuess("50", 1, 100)

        assertEquals(50, result)
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력할 경우 오류가 발생한다.")
    fun 숫자_아닌_값_입력시_오류2() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessService.parseAndValidateNumberGuess("abc", 1, 100)
        }

        assertEquals(Errors.INVALID_GAMETYPE, example.message)
    }

    @Test
    @DisplayName("범위 밖의 숫자를 입력하면 오류가 발생한다.")
    fun 범위_밖의_값_입력시_오류() {
        val exampleOne = assertThrows(IllegalArgumentException::class.java) {
            guessService.parseAndValidateNumberGuess("0", 1, 100)
        }
        val exampleTwo = assertThrows(IllegalArgumentException::class.java) {
            guessService.parseAndValidateNumberGuess("101", 1, 100)
        }

        assertEquals(Errors.NUMBER_OUT_OF_RANGE, exampleOne.message)
        assertEquals(Errors.NUMBER_OUT_OF_RANGE, exampleTwo.message)
    }

    @Test
    @DisplayName("숫자가 정답보다 작으면 UP을 반환한다.")
    fun 숫자_정답보다_작을시_UP() {
        val result = guessService.judgeNumberGuess(answer = 50, guess = 30)

        assertEquals(GuessResult.UP, result)
    }

    @Test
    @DisplayName("숫자가 정답보다 크면 DOWN을 반환한다.")
    fun 숫자_정답보다_클시_DOWN() {
        val result = guessService.judgeNumberGuess(answer = 50, guess = 80)

        assertEquals(GuessResult.DOWN, result)
    }

    @Test
    @DisplayName("숫자가 정답과 같으면 CORRECT를 반환한다.")
    fun 숫자_정답과_같을시_CORRECT() {
        val result = guessService.judgeNumberGuess(answer = 50, guess = 50)

        assertEquals(GuessResult.CORRECT, result)
    }

    @Test
    @DisplayName("랜덤 알파벳 생성은 RandomGenerator가 담당한다.")
    fun 랜덤_알바벳_생성_RandomGenerator_담당() {
        val fixedService = GuessService(FixedRandomGenerator(alphabet = 'x'))

        val answer = fixedService.generateAlphabetAnswer()

        assertEquals('x', answer)
    }

    @Test
    @DisplayName("알파벳을 한글자 입력하면 그대로 Char로 반환된다.")
    fun 알파벳_한글자_입력시_Char_반환() {
        val result = guessService.parseAndValidateAlphabetGuess("A", 'A', 'z')

        assertEquals('A', result)
    }

    @Test
    @DisplayName("두 글자 이상 입력하면 오류가 발생한다.")
    fun 두글자_이상_입력시_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessService.parseAndValidateAlphabetGuess("AB", 'A', 'z')
        }

        assertEquals(Errors.INVALID_GAMETYPE, example.message)
    }

    @Test
    @DisplayName("문자가 아닌 값을 입력하면 오류가 발생한다.")
    fun 문자_아닌_값_입력시_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessService.parseAndValidateAlphabetGuess("1", 'A', 'z')
        }

        assertEquals(Errors.INVALID_GAMETYPE, example.message)
    }

    @Test
    @DisplayName("알파벳 범위 밖 문자 입력시 오류가 발생한다.")
    fun 알파벳_범위_밖_문자_입력시_오류() {
        val example = assertThrows(IllegalArgumentException::class.java) {
            guessService.parseAndValidateAlphabetGuess("가", 'A', 'z')
        }

        assertEquals(Errors.ALPHABET_OUT_OF_RANGE, example.message)
    }

    @Test
    @DisplayName("알파벳 정답보다 앞에 있으면 UP 반환한다.")
    fun 알파벳_정답보다_앞에_있으면_UP() {
        val result = guessService.judgeAlphabetGuess(answer = 'm', guess = 'a')

        assertEquals(GuessResult.UP, result)
    }

    @Test
    @DisplayName("알파벳 정답보다 뒤에 있으면 DOWN 반환한다.")
    fun 알파벳_정답보다_뒤에_있으면_DOWN() {
        val result = guessService.judgeAlphabetGuess(answer = 'm', guess = 'z')

        assertEquals(GuessResult.DOWN, result)
    }

    @Test
    @DisplayName("알파벳 정답과 같으면 CORRECT 반환한다.")
    fun 알파벳_정답과_같으면_CORRECT() {
        val result = guessService.judgeAlphabetGuess(answer = 'm', guess = 'm')

        assertEquals(GuessResult.CORRECT, result)
    }
}
