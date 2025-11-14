package com.techcourse.openmission.lotto.domain

class WinningNumber(
    private val winningNumbers: Set<Int>,
    private val winningBonusNumber: Int
) {
    fun countSameNumbers(lotto: Lotto): Int {
        return lotto.countSameNumbers(winningNumbers)
    }

    fun isBonusNumberContains(lotto: Lotto): Boolean {
        return lotto.isBonusNumberContains(winningBonusNumber)
    }
}
