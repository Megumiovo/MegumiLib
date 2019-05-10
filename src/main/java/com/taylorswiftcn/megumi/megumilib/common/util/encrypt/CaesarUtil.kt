package com.taylorswiftcn.megumi.megumilib.common.util.encrypt

import java.lang.StringBuilder

class CaesarUtil {

    companion object {

        fun encrypt(string: String, key: Int):String {
            val sb = StringBuilder()

            for (c in string.toCharArray()) {
                var value = c.toInt()
                value += key
                sb.append(value.toChar())
            }

            return sb.toString()
        }

        fun decrypt(string: String, key: Int):String {
            val sb = StringBuilder()

            for (c in string.toCharArray()) {
                var value = c.toInt()
                value -= key
                sb.append(value.toChar())
            }

            return sb.toString()
        }

        fun megumi(string: String): List<String> {
            val list = arrayListOf<String>()

            for (i in 0..128) {
                list.add(decrypt(string, i))
                list.add(decrypt(string, -i))
            }

            return list
        }
    }
}