package com.techcourse.openmission.lotto.view

import com.techcourse.openmission.lotto.domain.Money
import com.techcourse.openmission.lotto.domain.WinningNumber
import com.techcourse.openmission.lotto.domain.constant.Messages
import com.techcourse.openmission.lotto.util.ParseAny
import com.techcourse.openmission.lotto.validator.LottoValidator
import java.util.TreeSet

class LottoInput {
    fun inputMoney(): Money {
        println(Messages.INPUT_MONEY)
        while (true) {
            try {
                val line = readln().trim()
                return Money(ParseAny.parseInt(line))
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun inputWinningAndBonusNumber(): WinningNumber {
        val winningNumbers = inputWinningNumber()
        while (true) {
            try {
                val bonusNumber = inputBonusNumber()

                LottoValidator.validateWinningAndBonusNumbers(winningNumbers, bonusNumber)

                return WinningNumber(winningNumbers, bonusNumber)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun inputWinningNumber(): Set<Int> {
        while (true) {
            println(Messages.INPUT_WINNING_NUMBERS)
            val winningNumbers = readln().trim()
            try {
                val numbers = ParseAny.parseWinningNumbers(winningNumbers)
                LottoValidator.validateWinningNumbers(numbers)

                return TreeSet(numbers)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun inputBonusNumber(): Int {
        while (true) {
            println(Messages.INPUT_BONUS_NUMBER)
            try {
                val line = readln().trim()
                return ParseAny.parseBonusNumber(line)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}
