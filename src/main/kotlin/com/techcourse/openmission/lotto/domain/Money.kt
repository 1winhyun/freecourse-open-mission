package com.techcourse.openmission.lotto.domain

import com.techcourse.openmission.lotto.domain.constant.Numbers
import com.techcourse.openmission.lotto.validator.LottoValidator

class Money(
    val amount: Int
) {
    init {
        LottoValidator.validateMoney(amount)
    }

    fun lottoPrice(): Int = Numbers.LOTTO_PRICE
}
