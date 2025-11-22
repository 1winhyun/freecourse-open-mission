package com.techcourse.openmission.guessing.domain

import com.techcourse.openmission.guessing.contstant.Errors

enum class GameType(val code: Int) {
    NUMBER(1),
    ALPHABET(2);

    companion object {
        fun from(input: String): GameType {
            val code = input.toIntOrNull()
                ?: throw IllegalArgumentException(Errors.INVALID_GAMETYPE)

            return GameType.entries.find { it.code == code } ?: throw IllegalArgumentException(Errors.INVALID_VERSION);
        }
    }
}