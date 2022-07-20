package com.xbaimiao.pay.deposit

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import taboolib.common.platform.function.submit

/**
 * @Author xbaimiao
 * @Date 2021/12/10 18:45
 */
class PayCommand(
    val name: String,
    val amount: Double,
    private val commands: List<String>
) {

    fun execCommands(player: OfflinePlayer) {
        submit {
            commands.forEach { command ->
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.name!!))
            }
        }
    }

}