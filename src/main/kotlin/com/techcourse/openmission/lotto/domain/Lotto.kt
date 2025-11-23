package com.techcourse.openmission.lotto.domain

import com.techcourse.openmission.lotto.validator.LottoValidator

class Lotto(
    val numbers: List<Int>
) {

    init {
        LottoValidator.validateLottoNumbers(numbers)
    }

    fun countSameNumbers(numbers: Set<Int>): Int {
        var count = 0
        for (number in this.numbers) {
            if (numbers.contains(number)) {
                count++
            }
        }

        return count
    }

    fun isBonusNumberContains(number: Int): Boolean {
        return numbers.contains(number)
    }

    override fun toString(): String {
        return numbers.toString()
    }
}
