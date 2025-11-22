package com.techcourse.openmission.guessing.service

import com.techcourse.openmission.guessing.contstant.Errors
import com.techcourse.openmission.guessing.contstant.Numbers
import com.techcourse.openmission.guessing.domain.GameType
import com.techcourse.openmission.guessing.domain.GuessResult

class GuessService(
    private val randomGenerator: RandomGenerator = DefaultRandomGenerator()
) {
    fun parseGameType(raw: String): GameType {
        val code = raw.toIntOrNull()
            ?: throw IllegalArgumentException(Errors.INVALID_GAMETYPE)

        return when (code) {
            1 -> GameType.NUMBER
            2 -> GameType.ALPHABET
            else -> throw IllegalArgumentException(Errors.INVALID_VERSION)
        }
    }

    fun generateNumberAnswer(): Int =
        randomGenerator.generateRandomNumber(Numbers.MIN_NUMBER, Numbers.MAX_NUMBER)

    fun parseAndValidateNumberGuess(raw: String, min: Int, max: Int): Int {
        val guess = raw.toIntOrNull()
            ?: throw IllegalArgumentException(Errors.INVALID_GAMETYPE)

        if (guess !in min..max) {
            throw IllegalArgumentException(Errors.NUMBER_OUT_OF_RANGE)
        }
        return guess
    }

    fun judgeNumberGuess(answer: Int, guess: Int): GuessResult =
        when {
            guess < answer -> GuessResult.UP
            guess > answer -> GuessResult.DOWN
            else -> GuessResult.CORRECT
        }

    fun generateAlphabetAnswer(): Char =
        randomGenerator.generateRandomAlphabet()

    fun parseAndValidateAlphabetGuess(raw: String, min: Char, max: Char): Char {
        if (raw.length != 1) {
            throw IllegalArgumentException(Errors.INVALID_GAMETYPE)
        }

        val c = raw[0]

        if (!c.isLetter()) {
            throw IllegalArgumentException(Errors.INVALID_GAMETYPE)
        }

        if (c < min || c > max) {
            throw IllegalArgumentException(Errors.ALPHABET_OUT_OF_RANGE)
        }
        return c
    }

    fun judgeAlphabetGuess(answer: Char, guess: Char): GuessResult =
        when {
            guess < answer -> GuessResult.UP
            guess > answer -> GuessResult.DOWN
            else -> GuessResult.CORRECT
        }
}
