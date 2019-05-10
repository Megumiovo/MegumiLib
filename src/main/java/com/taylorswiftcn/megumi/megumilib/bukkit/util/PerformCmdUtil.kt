package com.taylorswiftcn.megumi.megumilib.bukkit.util

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class PerformCmdUtil {
    companion object {

        fun execution(player: Player, commands: List<String>) {
            for (cmd in commands) {
                val command = cmd.replace("%player%", player.name)

                if (cmd.startsWith("[player]")) {
                    playerCmd(player, command.replace("[player]", ""))
                    return
                }

                if (cmd.startsWith("[op]")) {
                    opCmd(player, command.replace("[op]", ""))
                    return
                }

                if (cmd.startsWith("[console]")) {
                    consoleCmd(command.replace("[console]", ""))
                    return
                }

                playerCmd(player, cmd)
            }
        }

        fun playerCmd(player: Player, command: String) {
            val event = PlayerCommandPreprocessEvent(player, "/$command")
            Bukkit.getPluginManager().callEvent(event)
            if (event.isCancelled) return

            player.performCommand(event.message.replace("/", ""))
        }

        fun opCmd(player: Player, command: String) {
            if (player.isOp) {
                playerCmd(player, command)
                return
            }

            val string = command.replace("%player%", player.name)
            player.isOp = true
            player.performCommand(string)
            player.isOp = false
        }

        fun consoleCmd(command: String) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command)
        }
    }
}