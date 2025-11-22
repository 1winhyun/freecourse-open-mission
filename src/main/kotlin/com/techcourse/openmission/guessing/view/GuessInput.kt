package com.techcourse.openmission.guessing.view

import com.techcourse.openmission.guessing.contstant.Messages

class GuessInput {
    fun readVersion(): String {
        print(Messages.INPUT_VERSION)
        return readLine()?.trim().orEmpty()
    }

    fun readNumber(min: Int, max: Int): String {
        print(Messages.INPUT_NUMBER)
        return readLine()?.trim().orEmpty()
    }

    fun readAlphabet(min: Char, max: Char): String {
        print(Messages.INPUT_ALPHABET)
        return readLine()?.trim().orEmpty()
    }
}
