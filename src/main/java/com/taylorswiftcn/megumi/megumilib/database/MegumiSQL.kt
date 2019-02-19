package com.taylorswiftcn.megumi.megumilib.database

import java.sql.Connection
import java.sql.ResultSet

abstract class MegumiSQL {
    abstract fun openConnection()

    abstract fun closeConnection()

    abstract fun getConnection(): Connection?

    abstract fun checkConnection(): Boolean

    abstract fun querySQL(query: String): ResultSet?

    abstract fun updateSQL(update: String)
}