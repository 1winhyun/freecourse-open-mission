package com.techcourse.openmission.guessing.view

class GuessOutput {
    fun printGameStart() {
        println("업다운 게임을 시작합니다.\n")
    }

    fun printUp() = println("UP")

    fun printDown() = println("DOWN")

    fun printCorrect() = println("정답!")

    fun printError(message: String) = println(message)

    fun printTryCount(count: Int) {
        println()
        println("시도한 횟수 : ${count}회")
    }
}