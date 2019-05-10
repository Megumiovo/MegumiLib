package com.taylorswiftcn.megumi.megumilib.common.database.sqlite

import com.taylorswiftcn.megumi.megumilib.MegumiLib
import com.taylorswiftcn.megumi.megumilib.common.database.MegumiSQL
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*

class SqliteHandler constructor(private var datebase: String): MegumiSQL() {

    private val plugin: MegumiLib = MegumiLib.getInstance()
    private var connection: Connection? = null
    private var properties: Properties = Properties()

    init {
        properties["date_string_format"] = "yyyy-MM-dd HH:mm:ss"
    }

    override fun openConnection() {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:${plugin.dataFolder}/$datebase.db", properties)

        } catch (e: SQLException) {
            plugin.logger.info("连接数据库失败!")
            connection = null

        } catch (e: ClassNotFoundException) {
            plugin.logger.info("未找到JDBC驱动程序,连接数据库失败!")

        }
    }

    override fun closeConnection() {
        try {
            connection!!.close()
            connection = null
        }
        catch (e: SQLException) {
            plugin.logger.info("关闭数据库连接失败!")
        }
    }

    override fun getConnection(): Connection? {
        return connection
    }

    override fun checkConnection(): Boolean {
        return connection != null
    }

    override fun querySQL(query: String): ResultSet? {
        try {
            val stat = connection!!.createStatement() ?: return null
            return stat.executeQuery(query)

        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

    @Synchronized override fun updateSQL(update: String) {
        try {
            val stat = connection!!.createStatement()
            stat.executeUpdate(update)

        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}