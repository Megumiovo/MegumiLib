package com.taylorswiftcn.megumi.megumilib

import org.bukkit.plugin.java.JavaPlugin

class MegumiLib : JavaPlugin() {

    companion object {
        private lateinit var instance: MegumiLib

        fun getInstance(): MegumiLib {
            return instance
        }
    }

    override fun onEnable() {
        instance = this
        logger.info("Success enable!")
    }

    override fun onDisable() {
        logger.info("Success disable!")
    }
}