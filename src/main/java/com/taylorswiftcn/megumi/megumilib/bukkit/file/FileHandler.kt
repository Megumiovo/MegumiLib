package com.taylorswiftcn.megumi.megumilib.bukkit.file

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class FileHandler constructor(private val plugin: JavaPlugin) {

    fun initFile(fileName: String): YamlConfiguration {
        val file = File(plugin.dataFolder, fileName)
        if (!file.exists()) {
            file.parentFile.mkdirs()
            copyFile(plugin.getResource(fileName), file)
            plugin.logger.info("File: Create $fileName file success")
        }
        else {
            plugin.logger.info("File: Load $fileName file success")
        }

        return YamlConfiguration.loadConfiguration(file)
    }

    fun saveFile(fileName: String, yamlConfiguration: YamlConfiguration) {
        val file = File(plugin.dataFolder, fileName)
        yamlConfiguration.save(file)
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