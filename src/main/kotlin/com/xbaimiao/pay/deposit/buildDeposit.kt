package com.xbaimiao.pay.deposit

import org.bukkit.entity.Player
import com.xbaimiao.pay.utils.Trade

fun buildDeposit(
    player: Player,
    name: String,
    amount: Double,
    type: DepositType,
    time: Int = 300,
    order: String = Trade.createOutTradeNo(),
    timeout: Deposit.() -> Unit = {},
    func: Deposit .() -> Unit
) {
    Deposit(DepositInfo(order, amount, name, player, type), time, timeout, func)
}