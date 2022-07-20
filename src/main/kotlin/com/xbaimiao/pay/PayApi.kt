package com.xbaimiao.pay

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import com.xbaimiao.pay.deposit.DepositType
import com.xbaimiao.pay.deposit.PayCommand
import com.xbaimiao.pay.deposit.buildDeposit
import com.xbaimiao.pay.utils.Service
import taboolib.common.platform.Plugin
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.command
import taboolib.common.platform.function.info
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

object PayApi : Plugin() {

    @Config(value = "key.yml")
    lateinit var key: ConfigFile
        private set

    @Config(value = "config.yml")
    lateinit var config: ConfigFile
        private set

    val payCommands = ArrayList<PayCommand>()

    override fun onEnable() {
        Service.load()
        loadPayCommand()
        info("加载了${payCommands.size}个人民币物品")
        command(
            "payapi",
            permissionDefault = PermissionDefault.TRUE
        ) {
            literal("reload", permission = "op") {
                execute<CommandSender> { sender, _, _ ->
                    payCommands.clear()
                    loadPayCommand()
                    sender.sendMessage("加载了${payCommands.size}个人民币物品")
                }
            }
            dynamic {
                suggestion<Player> { _, _ ->
                    payCommands.map { it.name }
                }
                execute<Player> { player, _, argument ->
                    val payCommand = payCommands.find { it.name == argument } ?: return@execute
                    buildDeposit(
                        player = player,
                        name = payCommand.name,
                        payCommand.amount,
                        DepositType.WX
                    ) {
                        payCommand.execCommands(player)
                    }
                }
            }
        }
    }

    private fun loadPayCommand() {
        for (key in config.getKeys(false)) {
            payCommands.add(
                PayCommand(
                    key,
                    config.getDouble("$key.amount"),
                    config.getStringList("$key.commands")
                )
            )
        }
    }

}