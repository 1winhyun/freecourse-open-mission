package com.techcourse.openmission.guessing

import com.techcourse.openmission.guessing.domain.GuessResult
import com.techcourse.openmission.guessing.service.GuessService
import com.techcourse.openmission.guessing.service.RandomGenerator
import org.junit.jupiter.api.Assertions.assertEquals
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
    @DisplayName("랜덤 숫자 생성은 RandomGenerator에 위임한다.")
    fun 랜덤_숫자_생성은_RandomGenerator가_수행() {
        val fixedService = GuessService(FixedRandomGenerator(number = 77))

        val answer = fixedService.generateNumberAnswer()

        assertEquals(77, answer)
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
