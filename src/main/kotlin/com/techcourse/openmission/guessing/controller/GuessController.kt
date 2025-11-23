package com.techcourse.openmission.guessing.controller

import com.techcourse.openmission.guessing.contstant.Alphabets
import com.techcourse.openmission.guessing.contstant.Errors
import com.techcourse.openmission.guessing.contstant.Numbers
import com.techcourse.openmission.guessing.domain.GameType
import com.techcourse.openmission.guessing.domain.GuessResult
import com.techcourse.openmission.guessing.service.GuessService
import com.techcourse.openmission.guessing.valitdator.GuessValidator
import com.techcourse.openmission.guessing.view.GuessInput
import com.techcourse.openmission.guessing.view.GuessOutput

class GuessController(
    private val guessService: GuessService = GuessService(),
    private val guessValidator: GuessValidator = GuessValidator(),
    private val guessOutput: GuessOutput = GuessOutput(),
    private val guessInput: GuessInput = GuessInput()
) {
    fun run() {
        guessOutput.printGameStart()

        val gameType = readGameType()

        val attempts = when (gameType) {
            GameType.NUMBER -> playNumberGame()
            GameType.ALPHABET -> playAlphabetGame()
        }

        guessOutput.printTryCount(attempts)
    }

    private fun readGameType(): GameType {
        while (true) {
            val raw = guessInput.readVersion()
            try {
                return guessValidator.parseGameType(raw)
            } catch (e: IllegalArgumentException) {
                guessOutput.printError(e.message ?: Errors.INVALID_GAMETYPE)
            }
        }
    }

    private fun playNumberGame(): Int {
        var min = Numbers.MIN_NUMBER
        var max = Numbers.MAX_NUMBER
        val answer = guessService.generateNumberAnswer()
        var attempts = 0

        while (true) {
            val raw = guessInput.readNumber()
            attempts++

            try {
                val guess = guessValidator.parseAndValidateNumberGuess(raw, min, max)
                val result = guessService.judgeNumberGuess(answer, guess)

                when (result) {
                    GuessResult.UP -> {
                        guessOutput.printUp()
                        min = guess + 1
                    }

                    GuessResult.DOWN -> {
                        guessOutput.printDown()
                        max = guess - 1
                    }

                    GuessResult.CORRECT -> {
                        guessOutput.printCorrect()
                        return attempts
                    }
                }
            } catch (e: IllegalArgumentException) {
                guessOutput.printError(e.message ?: Errors.INVALID_GAMETYPE)
            }
        }
    }

    private fun playAlphabetGame(): Int {
        var min = Alphabets.MIN_ALPHABET
        var max = Alphabets.MAX_ALPHABET
        val answer = guessService.generateAlphabetAnswer()
        var attempts = 0

        while (true) {
            val raw = guessInput.readAlphabet()
            attempts++

            try {
                val guess = guessValidator.parseAndValidateAlphabetGuess(raw, min, max)
                val result = guessService.judgeAlphabetGuess(answer, guess)

                when (result) {
                    GuessResult.UP -> {
                        guessOutput.printUp()
                        min = guess + 1
                    }

                    GuessResult.DOWN -> {
                        guessOutput.printDown()
                        max = guess - 1
                    }

                    GuessResult.CORRECT -> {
                        guessOutput.printCorrect()
                        return attempts
                    }
                }
            } catch (e: IllegalArgumentException) {
                guessOutput.printError(e.message ?: Errors.INVALID_GAMETYPE)
            }
        }
    }
}
