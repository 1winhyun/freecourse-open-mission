package com.techcourse.openmission.racingcar.domain

import com.techcourse.openmission.racingcar.domain.rule.MoveRule

class Car(
    val name: String,
    var location: Int = 0
) {
    fun moveIfAllowed(moveRule: MoveRule, randomNumber: Int) {
        if (moveRule.canMove(randomNumber)) {
            location++;
        }
    }
}
