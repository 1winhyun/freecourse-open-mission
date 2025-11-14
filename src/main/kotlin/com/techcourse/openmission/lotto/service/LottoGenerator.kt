package com.techcourse.openmission.lotto.service

import com.techcourse.openmission.lotto.domain.constant.Numbers
import kotlin.random.Random

class LottoGenerator {
    fun generateLotto(): List<Int> {
        val numbers = mutableSetOf<Int>()

        while (numbers.size < Numbers.LOTTO_NUMBERS_COUNT) {
            val randomNumber = Random.nextInt(
                from = Numbers.MIN_NUMBER,
                until = Numbers.MAX_NUMBER + 1
            )
            numbers += randomNumber
        }

        return numbers.toList().sorted()
    }
}
