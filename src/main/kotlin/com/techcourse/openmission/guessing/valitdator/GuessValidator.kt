package com.techcourse.openmission.guessing.valitdator

import com.techcourse.openmission.guessing.contstant.Errors
import com.techcourse.openmission.guessing.domain.GameType

class GuessValidator {
    fun parseGameType(raw: String?): GameType {
        val code = raw?.toIntOrNull()
            ?: throw IllegalArgumentException(Errors.INVALID_GAMETYPE)

        return when (code) {
            1 -> GameType.NUMBER
            2 -> GameType.ALPHABET
            else -> throw IllegalArgumentException(Errors.INVALID_VERSION)
        }
    }

    fun parseAndValidateNumberGuess(raw: String?, min: Int, max: Int): Int {
        val guess = raw?.toIntOrNull()
            ?: throw IllegalArgumentException(Errors.INVALID_GAMETYPE)

        if (guess !in min..max) {
            throw IllegalArgumentException(Errors.NUMBER_OUT_OF_RANGE)
        }
        return guess
    }

    fun parseAndValidateAlphabetGuess(raw: String?, min: Char, max: Char): Char {
        if (raw.isNullOrBlank() || raw.length != 1) {
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
}