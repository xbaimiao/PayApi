package org.serverct.pay.deposit

import org.bukkit.entity.Player
import org.serverct.pay.utils.Trade

/**
 * @Author xbaimiao
 * @Date 2021/10/20 18:51
 */
class DepositInfo(
    val orderId: String = Trade.createOutTradeNo(),
    val amount: Double,
    val name: String,
    val player: Player,
    val type: DepositType
)