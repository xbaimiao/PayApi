package com.xbaimiao.pay.deposit

import org.bukkit.entity.Player
import com.xbaimiao.pay.utils.Trade

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