package com.taylorswiftcn.megumi.megumilib.file

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class FileHandler constructor(plugin: JavaPlugin) {
    private lateinit var plugin: JavaPlugin

    fun initFile(fileName: String): YamlConfiguration {
        val file = File(plugin.dataFolder, fileName)
        if (!file.exists()) {
            file.parentFile.mkdirs()
            copyFile(plugin.getResource(fileName), file)
            plugin.logger.info(String.format("File: Create %s file success", fileName))
        }
        else {
            plugin.logger.info(String.format("File: Load %s file success", fileName))
        }

        return YamlConfiguration.loadConfiguration(file)
    }

    private fun copyFile(inputStream: InputStream, file: File) {
        try {
            val fileOutputStream = FileOutputStream(file)
            val arrayOfByte = ByteArray(63)
            var i: Int

            do {
                i = inputStream.read(arrayOfByte)
                if (i < 0) break
                fileOutputStream.write(arrayOfByte, 0, i)
            } while (true)

            fileOutputStream.close()
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}