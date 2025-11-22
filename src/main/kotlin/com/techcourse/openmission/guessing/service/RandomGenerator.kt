package com.techcourse.openmission.guessing.service

interface RandomGenerator {
    fun generateRandomNumber(min: Int = 1, max: Int = 100): Int
    fun generateRandomAlphabet(): Char
}
