package org.serverct.pay.deposit

import org.bukkit.entity.Player
import org.serverct.pay.utils.Trade

/**
 * @Author xbaimiao
 * @Date 2021/10/20 21:46
 */
object BuildDeposit {

    fun Player.pay(
        name: String,
        amount: Double,
        type: DepositType,
        order: String = Trade.createOutTradeNo(),
        func: Deposit .() -> Unit
    ) {
        Deposit(DepositInfo(order, amount, name, this, type), func)
    }

}