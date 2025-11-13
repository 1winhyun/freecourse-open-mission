package com.techcourse.openmission.racingcar.domain.validator

class RacingCarValidator {
    fun validateName(name: String?) {
        if (name.isNullOrBlank()) {
            throw IllegalArgumentException("자동차 이름은 공백일 수 없습니다.")
        }

        if (name.length > 5) {
            throw IllegalArgumentException("자동차 이름은 5글자를 넘을 수 없습니다.")
        }
    }

    fun parseAndValidateAttempts(input: String?): Int {
        if (input == null) {
            throw IllegalArgumentException()
        }

        val inputSentence = input.trim()
        if (inputSentence.isEmpty()) {
            throw IllegalArgumentException("시도 횟수를 입력하지 않았습니다.")
        }

        val number = parseToInt(inputSentence)
        if (number <= 0) {
            throw IllegalArgumentException("시도 횟수는 반드시 1 이상이어야 합니다.")
        }

        return number
    }

    private fun parseToInt(sentence: String): Int {
        return try {
            sentence.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("시도 횟수는 반드시 숫자로 입력해야합니다.")
        }
    }
}
