package com.taylorswiftcn.megumi.megumilib.commands.util

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class PerformCmdUtil {
    companion object {

        fun execution(player: Player, commands: List<String>) {
            for (cmd in commands) {
                if (cmd.startsWith("[player]")) {
                    playerCmd(player, cmd.replace("[player]", ""))
                    return
                }

                if (cmd.startsWith("[op]")) {
                    opCmd(player, cmd.replace("[op]", ""))
                    return
                }

                if (cmd.startsWith("[console]")) {
                    consoleCmd(cmd.replace("[console]", ""))
                    return
                }

                playerCmd(player, cmd)
            }
        }

        fun playerCmd(player: Player, command: String) {
            val string = command.replace("%player%", player.name)

            val event = PlayerCommandPreprocessEvent(player, "/$string")
            Bukkit.getPluginManager().callEvent(event)
            if (event.isCancelled) return

            player.performCommand(string)
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