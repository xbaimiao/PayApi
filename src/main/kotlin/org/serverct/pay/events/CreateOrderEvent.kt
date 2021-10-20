package org.serverct.pay.events

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.serverct.pay.deposit.Deposit

class CreateOrderEvent(val deposit: Deposit) : Event(true) {
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    val player: Player
        get() = deposit.info.player
    val amount: Double
        get() = deposit.info.amount

    companion object {
        @JvmStatic
        val handlerList = HandlerList()
    }
}