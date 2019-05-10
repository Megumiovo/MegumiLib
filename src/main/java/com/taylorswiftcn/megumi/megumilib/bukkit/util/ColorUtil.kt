package com.taylorswiftcn.megumi.megumilib.bukkit.util

import java.util.ArrayList

class ColorUtil {
    companion object {

        fun replace(_string: String): String {
            return _string.replace("§", "&")
        }

        fun replace(_strings: List<String>): List<String> {
            val strings = ArrayList<String>()

            for (s in _strings) {
                strings.add(replace(s))
            }

            return strings
        }
    }
}