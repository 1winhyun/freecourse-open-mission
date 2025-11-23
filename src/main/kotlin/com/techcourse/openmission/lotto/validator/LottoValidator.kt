package com.techcourse.openmission.lotto.validator

import com.techcourse.openmission.lotto.domain.constant.Errors
import com.techcourse.openmission.lotto.domain.constant.Numbers
import java.util.TreeSet

object LottoValidator {
    fun validateLottoNumbers(numbers: List<Int>) {
        if (numbers.size != Numbers.LOTTO_NUMBERS_COUNT) {
            throw IllegalArgumentException(Errors.NUMBER_SIZE)
        }
        if (!iSNumberInRange(numbers)) {
            throw IllegalArgumentException(Errors.NUMBER_RANGE)
        }
        if (!isUnique(numbers)) {
            throw IllegalArgumentException(Errors.NUMBER_DUPLICATE)
        }
    }

    fun validateMoney(amount: Int) {
        if (amount < Numbers.LOTTO_PRICE) {
            throw IllegalArgumentException(Errors.MONEY_MIN)
        }
        if (amount % Numbers.LOTTO_PRICE != 0) {
            throw IllegalArgumentException(Errors.MONEY_UNIT)
        }
    }

    fun validateWinningNumbers(winningNumbers: List<Int>) {
        if (winningNumbers.size != Numbers.LOTTO_NUMBERS_COUNT) {
            throw IllegalArgumentException(Errors.WINNING_NUMBER_SIZE)
        }

        for (winningNumber in winningNumbers) {
            requireRange(winningNumber)
        }

        if (!isUnique(winningNumbers)) {
            throw IllegalArgumentException(Errors.WINNING_NUMBER_DUPLICATE)
        }
    }

    fun validateWinningAndBonusNumbers(winningNumbers: Set<Int>, winningBonusNumber: Int) {
        for (winningNumber in winningNumbers) {
            requireRange(winningNumber)
        }

        if (winningNumbers.contains(winningBonusNumber)) {
            throw IllegalArgumentException(Errors.WINNING_BONUS_DUPLICATE)
        }
    }

    fun requireRange(number: Int) {
        if (number < Numbers.MIN_NUMBER || number > Numbers.MAX_NUMBER) {
            throw IllegalArgumentException(Errors.WINNING_BONUS_NUMBER_RANGE)
        }
    }

    fun ifWinningNumbersNull(winningNumbers: String?) {
        if (winningNumbers.isNullOrEmpty()) {
            throw IllegalArgumentException(Errors.WINNING_NUMBER_NULL)
        }
    }

    fun ifBonusNumberSingle(bonusNumber: String?): Array<String> {
        if (bonusNumber.isNullOrEmpty()) {
            throw IllegalArgumentException(Errors.BONUS_NUMBER_NULL)
        }

        val numbers = bonusNumber.trim().split(Regex("[,\\s]+")).toTypedArray()

        if (numbers.size != 1) {
            throw IllegalArgumentException(Errors.BONUS_NUMBER_SINGLE)
        }

        return numbers
    }

    private fun iSNumberInRange(numbers: List<Int>): Boolean {
        for (number in numbers) {
            if (number < Numbers.MIN_NUMBER || number > Numbers.MAX_NUMBER) {
                return false
            }
        }
        return true
    }

    private fun isUnique(numbers: List<Int>): Boolean {
        return TreeSet(numbers).size == Numbers.LOTTO_NUMBERS_COUNT
    }
}
