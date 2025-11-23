package com.techcourse.openmission.racingcar.domain.constant

object Errors {
    const val NAME_NULL = "[ERROR] 이름 입력이 비어있습니다."
    const val CAR_LESS_ONE = "[ERROR] 자동차는 한 대 이상이어야 합니다."
    const val CAR_NAME_NULL = "[ERROR] 자동차 이름은 공백일 수 없습니다."
    const val CAR_NAME_OVER_FIVE = "[ERROR] 자동차 이름은 5글자를 넘을 수 없습니다."
    const val ATTEMPT_NOT_WRITTEN = "[ERROR] 시도 횟수를 입력하지 않았습니다."
    const val ATTEMPT_UNDER_ONE = "[ERROR] 시도 횟수는 반드시 1 이상이어야 합니다."
    const val ATTEMPT_NOT_NUMBER="[ERROR] 시도 횟수는 반드시 숫자로 입력해야합니다."
}
