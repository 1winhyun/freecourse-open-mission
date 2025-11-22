package com.techcourse.openmission.guessing.service

import kotlin.random.Random

class DefaultRandomGenerator : RandomGenerator {

    override fun generateRandomNumber(min: Int, max: Int): Int {
        return Random.nextInt(min, max + 1)
    }

    override fun generateRandomAlphabet(): Char {
        val letters = ('A'..'Z') + ('a'..'z')
        return letters.random()
    }
}