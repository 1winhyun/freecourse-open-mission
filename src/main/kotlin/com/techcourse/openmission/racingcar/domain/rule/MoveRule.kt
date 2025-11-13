package com.techcourse.openmission.racingcar.domain.rule

fun interface MoveRule {
    fun canMove(randomNumber: Int): Boolean
}
