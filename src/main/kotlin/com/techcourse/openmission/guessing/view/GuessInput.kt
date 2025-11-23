package com.techcourse.openmission.guessing.view

import com.techcourse.openmission.guessing.contstant.Messages

class GuessInput {
    fun readVersion(): String {
        print(Messages.INPUT_VERSION)
        return readLine()?.trim().orEmpty()
    }

    fun readNumber(): String {
        print(Messages.INPUT_NUMBER)
        return readLine()?.trim().orEmpty()
    }

    fun readAlphabet(): String {
        print(Messages.INPUT_ALPHABET)
        return readLine()?.trim().orEmpty()
    }
}
