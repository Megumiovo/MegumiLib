package com.taylorswiftcn.megumi.megumilib.common.database.mysql

import java.util.*

class MysqlPool
constructor(
        var hostname: String,
        var port: String,
        var database: String,
        var username: String,
        var password: String
) {

    private lateinit var pools: LinkedList<MysqlHandler>
    private var min: Int = 0
    private var max: Int = 5

    fun setMin(min: Int) {
        this.min = min
    }

    fun setMax(max: Int) {
        this.max = max
    }

    fun create() {
        pools.clear()

        for (i in min..max) {
            pools.add(MysqlHandler(hostname, port, database, username, password))
        }
    }

    fun getHandler(): MysqlHandler {
        if (pools.size == 0) return MysqlHandler(hostname, port, database, username, password)
        return pools.removeAt(0)
    }

    fun recover(handler: MysqlHandler) {
        if (pools.size >= max) return
        pools.add(handler)
    }
}