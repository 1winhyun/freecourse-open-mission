package com.techcourse.openmission.lotto.domain.constant

object Errors {
    const val REQUIRE_NUMBER = "[ERROR] 숫자만 입력 가능합니다."
    const val NUMBER_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."
    const val NUMBER_SIZE = "[ERROR] 로또 번호는 6개여야 합니다."
    const val MONEY_MIN = "[ERROR] 로또는 최소 1000원부터입니다."
    const val MONEY_UNIT = "[ERROR] 로또 구입 금액은 1000원 단위입니다."
    const val NUMBER_DUPLICATE = "[ERROR] 로또 번호는 중복이 불가능합니다."
    const val WINNING_NUMBER_NULL = "[ERROR] 당첨 번호를 입력하세요."
    const val WINNING_BONUS_NUMBER_RANGE = "[ERROR] 당첨 및 보너스 번호는 1부터 45 사이의 숫자여야 합니다."
    const val WINNING_NUMBER_SIZE = "[ERROR] 당첨 번호는 6개여야 합니다."
    const val BONUS_NUMBER_NULL = "[ERROR] 보너스 번호를 입력하세요."
    const val BONUS_NUMBER_SINGLE = "[ERROR] 보너스 번호는 1개여야 합니다."
    const val WINNING_NUMBER_DUPLICATE = "[ERROR] 당첨 번호는 중복될 수 없습니다."
    const val WINNING_BONUS_DUPLICATE = "[ERROR] 당첨 번호와 보너스 번호는 동일할 수 없습니다."
}