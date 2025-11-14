package com.techcourse.openmission.lotto.domain

import com.techcourse.openmission.lotto.domain.constant.Numbers

class Money(
    val amount:Int
) {
    fun lottoPrice(): Int = Numbers.LOTTO_PRICE
}
