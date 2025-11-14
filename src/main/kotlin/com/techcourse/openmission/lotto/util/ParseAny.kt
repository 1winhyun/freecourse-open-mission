package com.techcourse.openmission.lotto.util

import com.techcourse.openmission.lotto.domain.constant.Errors
import com.techcourse.openmission.lotto.validator.LottoValidator

object ParseAny {
    fun parseWinningNumbers(winningNumbers: String?): List<Int> {
        LottoValidator.ifWinningNumbersNull(winningNumbers)

        val verifiedWinningNumbers = mutableListOf<Int>()

        winningNumbers!!
            .split(",")
            .forEach { token ->
                verifiedWinningNumbers += parseInt(token.trim())
            }

        return verifiedWinningNumbers
    }

    fun parseBonusNumber(bonusNumber: String?): Int {
        val numbers = LottoValidator.ifBonusNumberSingle(bonusNumber)
        return parseInt(numbers[0])
    }

    fun parseInt(numbers: String): Int {
        return try {
            numbers.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(Errors.REQUIRE_NUMBER)
        }
    }
}
