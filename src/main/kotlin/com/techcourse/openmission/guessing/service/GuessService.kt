package com.techcourse.openmission.guessing.service

import com.techcourse.openmission.guessing.contstant.Numbers
import com.techcourse.openmission.guessing.domain.GuessResult

class GuessService(
    private val randomGenerator: RandomGenerator = DefaultRandomGenerator()
) {

    fun generateNumberAnswer(): Int =
        randomGenerator.generateRandomNumber(Numbers.MIN_NUMBER, Numbers.MAX_NUMBER)

    fun judgeNumberGuess(answer: Int, guess: Int): GuessResult =
        when {
            guess < answer -> GuessResult.UP
            guess > answer -> GuessResult.DOWN
            else -> GuessResult.CORRECT
        }

    fun generateAlphabetAnswer(): Char =
        randomGenerator.generateRandomAlphabet()

    fun judgeAlphabetGuess(answer: Char, guess: Char): GuessResult =
        when {
            guess < answer -> GuessResult.UP
            guess > answer -> GuessResult.DOWN
            else -> GuessResult.CORRECT
        }
}
