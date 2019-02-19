package com.taylorswiftcn.megumi.megumilib.database.mysql

import com.taylorswiftcn.megumi.megumilib.MegumiLib
import com.taylorswiftcn.megumi.megumilib.database.MegumiSQL
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class MysqlHandler
constructor(
        private var hostname: String,
        private var port: String,
        private var database: String,
        private var username: String,
        private var password: String
) : MegumiSQL() {

    private val plugin: MegumiLib = MegumiLib.getInstance()
    private var connection: Connection? = null

    override fun openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s?useSSL=false", hostname, port, database), username, password)

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

    override fun updateSQL(update: String) {
        try {
            val stat = connection!!.createStatement()
            stat.executeUpdate(update)

        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

}
