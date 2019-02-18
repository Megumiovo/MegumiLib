package com.taylorswiftcn.megumi.megumilib.commands

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class MegumiCommand {
    private var isPlayer: Boolean = false
    private lateinit var player: Player

    fun isPlayer(): Boolean {
        return isPlayer
    }

    fun getPlayer(): Player? {
        return player
    }

    abstract fun perform(commandSender: CommandSender, strings: Array<String>)

    abstract fun playerOnly(): Boolean

    abstract fun getMainPermission(): String

    abstract fun getPermission(): String

    fun execute(commandSender: CommandSender, strings: Array<String>) {
        isPlayer = commandSender is Player

        if (isPlayer) {
            player = commandSender as Player
        }

        if (playerOnly() && !isPlayer) return

        if (!(commandSender.hasPermission(getMainPermission()) || commandSender.hasPermission(getPermission()))) {
            commandSender.sendMessage("No Permission!")
            return
        }

        perform(commandSender, strings)
    }
}