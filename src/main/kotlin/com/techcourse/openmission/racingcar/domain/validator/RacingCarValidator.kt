package com.techcourse.openmission.racingcar.domain.validator

import com.techcourse.openmission.racingcar.domain.constant.Errors

class RacingCarValidator {
    fun validateName(name: String?) {
        if (name.isNullOrBlank()) {
            throw IllegalArgumentException(Errors.CAR_NAME_NULL)
        }

        if (name.length > 5) {
            throw IllegalArgumentException(Errors.CAR_NAME_OVER_FIVE)
        }
    }

    fun parseAndValidateAttempts(input: String?): Int {
        if (input == null) {
            throw IllegalArgumentException()
        }

        val inputSentence = input.trim()
        if (inputSentence.isEmpty()) {
            throw IllegalArgumentException(Errors.ATTEMPT_NOT_WRITTEN)
        }

        val number = parseToInt(inputSentence)
        if (number <= 0) {
            throw IllegalArgumentException(Errors.ATTEMPT_UNDER_ONE)
        }

        return number
    }

    private fun parseToInt(sentence: String): Int {
        return try {
            sentence.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(Errors.ATTEMPT_NOT_NUMBER)
        }
    }
}
