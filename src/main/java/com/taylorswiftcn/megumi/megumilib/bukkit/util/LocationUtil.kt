package com.taylorswiftcn.megumi.megumilib.bukkit.util

import org.bukkit.Bukkit
import org.bukkit.Location
import org.json.simple.JSONObject
import org.json.simple.JSONValue

class LocationUtil {

    companion object {
        fun convert(location: Location): String {
            val obj = JSONObject()
            obj["world"] = location.world.name
            obj["x"] = location.x
            obj["y"] = location.y
            obj["z"] = location.y
            obj["yaw"] = location.yaw
            obj["pitch"] = location.pitch

            return obj.toJSONString()
        }

        fun convert(string: String): Location {
            val obj = JSONValue.parse(string) as JSONObject

            val world = Bukkit.getWorld(obj["world"].toString())
            val x = obj["x"] as Double
            val y = obj["y"] as Double
            val z = obj["z"] as Double
            val yaw = obj["yaw"] as Float
            val pitch = obj["pitch"] as Float

            return Location(world, x, y, z, yaw, pitch)
        }
    }

}