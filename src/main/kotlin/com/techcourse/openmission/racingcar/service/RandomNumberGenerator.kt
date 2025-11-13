package com.techcourse.openmission.racingcar.service

import kotlin.random.Random

class RandomNumberGenerator : NumberGenerator {
    override fun randomNumber(min: Int, max: Int): Int {
        return Random.nextInt(from = min, until = max + 1)
    }
}
