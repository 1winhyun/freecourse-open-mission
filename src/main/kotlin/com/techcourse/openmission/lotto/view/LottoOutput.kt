package com.techcourse.openmission.lotto.view

import com.techcourse.openmission.lotto.domain.Lotto
import com.techcourse.openmission.lotto.domain.LottoRank
import java.text.NumberFormat
import java.util.Locale

class LottoOutput {
    fun printBoughtLotto(lottos: List<Lotto>) {
        println("${lottos.size}개를 구매했습니다.")
        for (lotto in lottos) {
            println(lotto)
        }
    }

    fun printStats(lottoResult: Map<LottoRank, Int>, yield: String) {
        println()
        println("당첨 통계")
        println("---")

        val numberFormat = NumberFormat.getNumberInstance(Locale.KOREA)
        for (lottoRank in LottoRank.print()) {
            val stat = "${lottoRank.message()} (${numberFormat.format(lottoRank.prize())}원) - " +
                    "${lottoResult.getOrDefault(lottoRank, 0)}개"
            println(stat)
        }

        println("총 수익률은 $yield 입니다.")
    }
}
