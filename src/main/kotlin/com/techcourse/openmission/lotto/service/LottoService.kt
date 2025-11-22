package com.techcourse.openmission.lotto.service

import com.techcourse.openmission.lotto.domain.Lotto
import com.techcourse.openmission.lotto.domain.LottoRank
import com.techcourse.openmission.lotto.domain.Money
import com.techcourse.openmission.lotto.domain.WinningNumber
import com.techcourse.openmission.lotto.domain.constant.Numbers
import java.text.NumberFormat
import java.util.EnumMap
import java.util.Locale

class LottoService(
    private val lottoGenerator: LottoGenerator = LottoGenerator(),
) {
    fun butLotto(money: Money): List<Lotto> {
        val count = money.amount / money.lottoPrice()
        return List(count) {
            Lotto(lottoGenerator.generateLotto())
        }
    }

    fun winnerConfirm(lottos: List<Lotto>, number: WinningNumber): Map<LottoRank, Int> {
        val lottoResult = EnumMap<LottoRank, Int>(LottoRank::class.java)

        for (lotto in lottos) {
            val lottoRank = LottoRank.of(
                number.countSameNumbers(lotto),
                number.isBonusNumberContains(lotto)
            )

            if (lottoRank == LottoRank.NOTHING) {
                continue
            }

            val current = lottoResult[lottoRank] ?: 0
            lottoResult[lottoRank] = current + 1
        }

        return lottoResult
    }

    fun calculateYield(lottoResult: Map<LottoRank, Int>, money: Money): String {
        var income = 0L;
        for ((rank, count) in lottoResult) {
            income += rank.prize() * count.toLong()
        }

        val allCost = money.amount
        val yield = (income * Numbers.PERCENT_SCALE_DECIMAL + allCost / 2) / allCost

        val integer = yield / 10
        val firstDecimal = yield % 10

        val numberFormat = NumberFormat.getNumberInstance(Locale.KOREA)
        return "${numberFormat.format(integer)}.$firstDecimal%"
    }
}
