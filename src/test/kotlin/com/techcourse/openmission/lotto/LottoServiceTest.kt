package com.techcourse.openmission.lotto

import com.techcourse.openmission.lotto.domain.Lotto
import com.techcourse.openmission.lotto.domain.LottoRank
import com.techcourse.openmission.lotto.domain.Money
import com.techcourse.openmission.lotto.domain.WinningNumber
import com.techcourse.openmission.lotto.domain.constant.Numbers
import com.techcourse.openmission.lotto.service.LottoService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoServiceTest {
    private val lottoService = LottoService()

    @Test
    @DisplayName("구입 금액에 맞는 개수만큼 로또를 생성한다.")
    fun 구입_금액에_맞는_개수만큼_로또_생성() {
        val money = Money(8000)

        val lottos = lottoService.butLotto(money)

        assertEquals(8, lottos.size)
        lottos.forEach { lotto ->
            assertEquals(Numbers.LOTTO_NUMBERS_COUNT, lotto.numbers.size)
            lotto.numbers.forEach { number ->
                assertTrue(number in Numbers.MIN_NUMBER..Numbers.MAX_NUMBER)
            }

            assertEquals(
                lotto.numbers.size,
                lotto.numbers.toSet().size
            )
        }
    }

    @Test
    @DisplayName("당첨 결과에 따라 각 등수별 개수를 계산한다.")
    fun 결과에_따른_등수별_개수_계산() {
        val winning = WinningNumber(
            winningNumbers = setOf(1, 2, 3, 4, 5, 6),
            winningBonusNumber = 7
        )

        val lottos = listOf(
            // 1등: 6개 일치
            Lotto(listOf(1, 2, 3, 4, 5, 6)),
            // 2등: 5개 + 보너스
            Lotto(listOf(1, 2, 3, 4, 5, 7)),
            // 3등: 5개
            Lotto(listOf(1, 2, 3, 4, 5, 8)),
            // 4등: 4개
            Lotto(listOf(1, 2, 3, 4, 8, 9)),
            // 5등: 3개
            Lotto(listOf(1, 2, 3, 8, 9, 10)),
            // 미당첨
            Lotto(listOf(1, 2, 8, 9, 10, 11))
        )

        val result = lottoService.winnerConfirm(lottos, winning)

        assertEquals(1, result[LottoRank.FIRST_RANK])
        assertEquals(1, result[LottoRank.SECOND_RANK])
        assertEquals(1, result[LottoRank.THIRD_RANK])
        assertEquals(1, result[LottoRank.FOURTH_RANK])
        assertEquals(1, result[LottoRank.FIFTH_RANK])
        assertFalse(result.containsKey(LottoRank.NOTHING))
    }

    @Test
    @DisplayName("수익률을 소수점 첫째 자리까지만 계산한다.")
    fun 수익률_소수점_첫째_자리_계산() {
        val money = Money(8000)
        val lottoResult = mapOf(
            LottoRank.FIFTH_RANK to 1
        )

        val yield = lottoService.calculateYield(lottoResult, money)

        assertEquals("62.5%", yield)
    }

    @Test
    @DisplayName("LottoRank의 메서드는 일치하는 개수와 보너스 일치 여부로 등수를 계산한다.")
    fun 일치하는_개수와_보너스_일치_여부로_등수_계산() {
        assertEquals(LottoRank.FIRST_RANK, LottoRank.of(6, false))
        assertEquals(LottoRank.SECOND_RANK, LottoRank.of(5, true))
        assertEquals(LottoRank.THIRD_RANK, LottoRank.of(5, false))
        assertEquals(LottoRank.FOURTH_RANK, LottoRank.of(4, false))
        assertEquals(LottoRank.FIFTH_RANK, LottoRank.of(3, false))
        assertEquals(LottoRank.NOTHING, LottoRank.of(2, false))
    }

    @Test
    @DisplayName("print()는 낮은 등수부터 정렬한다.")
    fun 출력_낮은_등수부터_정렬() {
        val ranks = LottoRank.print()

        assertArrayEquals(
            arrayOf(
                LottoRank.FIFTH_RANK,
                LottoRank.FOURTH_RANK,
                LottoRank.THIRD_RANK,
                LottoRank.SECOND_RANK,
                LottoRank.FIRST_RANK
            ),
            ranks
        )
    }

    @Test
    @DisplayName("동일한 숫자가 몇개 있는지 계산한다.")
    fun 동일_숫자_몇개인지_계산() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val sameCount = lotto.countSameNumbers(setOf(2, 4, 6, 8))

        assertEquals(3, sameCount)
    }

    @Test
    @DisplayName("일치개수와 보너스 여부를 판단한다.")
    fun 일치_개수와_보너스_여부_판단() {
        val winning = WinningNumber(setOf(1, 2, 3, 4, 5, 6), 7)
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 7))

        assertEquals(5, winning.countSameNumbers(lotto))
        assertTrue(winning.isBonusNumberContains(lotto))
    }
}