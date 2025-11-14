package com.techcourse.openmission.lotto.domain

enum class LottoRank(
    private val sameBallCount: Int,
    private val isBonusBallSame: Boolean,
    private val prize: Long,
    private val message: String
) {
    FIRST_RANK(6, false, 2000000000, "6개 일치"),
    SECOND_RANK(5, true, 30000000, "5개 일치, 보너스 볼 일치"),
    THIRD_RANK(5, false, 1500000, "5개 일치"),
    FOURTH_RANK(4, false, 50000, "4개 일치"),
    FIFTH_RANK(3, false, 5000, "3개 일치"),
    NOTHING(0, false, 0, "미당첨");

    fun prize(): Long = prize
    fun message(): String = message

    companion object {
        private val BASIC: Map<Int, LottoRank> = mapOf(
            6 to FIRST_RANK,
            5 to THIRD_RANK,
            4 to FOURTH_RANK,
            3 to FIFTH_RANK,
        )

        fun of(sameBallCount: Int, isBonusBallSame: Boolean): LottoRank {
            if (sameBallCount == 5 && isBonusBallSame) {
                return SECOND_RANK
            }

            val lottoRank = BASIC[sameBallCount]
            if (lottoRank != null) {
                return lottoRank
            }

            return NOTHING
        }

        fun print(): Array<LottoRank> {
            return entries
                .filter { it != NOTHING }
                .sortedWith(
                    compareBy<LottoRank> { it.sameBallCount }
                        .thenBy { it.isBonusBallSame }
                )
                .toTypedArray()
        }
    }
}
