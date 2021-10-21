package org.serverct.pay.deposit

import org.bukkit.entity.Player
import org.serverct.pay.utils.Trade

fun buildDeposit(
    player: Player,
    name: String,
    amount: Double,
    type: DepositType,
    order: String = Trade.createOutTradeNo(),
    func: Deposit .() -> Unit
) {
    Deposit(DepositInfo(order, amount, name, player, type), func)
}