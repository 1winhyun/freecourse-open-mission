package com.techcourse.openmission.racingcar.domain.rule

class DefaultMoveRule : MoveRule {
    override fun canMove(randomNumber: Int): Boolean = randomNumber >= 4
}
