package com.techcourse.openmission.lotto.controller

import com.techcourse.openmission.lotto.service.LottoService
import com.techcourse.openmission.lotto.view.LottoInput
import com.techcourse.openmission.lotto.view.LottoOutput

class LottoController {
    private val lottoInput = LottoInput()
    private val lottoOutput = LottoOutput()
    private val lottoService = LottoService()

    fun run() {
        val money = lottoInput.inputMoney()

        val lottos = lottoService.butLotto(money)
        lottoOutput.printBoughtLotto(lottos)

        val winningNumber = lottoInput.inputWinningAndBonusNumber()

        val result = lottoService.winnerConfirm(lottos, winningNumber)
        val yield = lottoService.calculateYield(result, money)

        lottoOutput.printStats(result, yield)
    }
}
