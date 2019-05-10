package com.taylorswiftcn.megumi.megumilib.common.util.encrypt

import java.security.MessageDigest

class MD5Util {

    companion object {
        fun encrypt(string: String): String {
            val instance = MessageDigest.getInstance("MD5")
            val digest = instance.digest(string.toByteArray())
            return bytesToHexString(digest)
        }

        private fun bytesToHexString(byteArray: ByteArray): String {
            val buffer = StringBuffer()
            for (byte in byteArray) {
                val i = byte.toInt() and 0xff
                var hexString = Integer.toHexString(i)

                if (hexString.length == 1) {
                    hexString = "0$hexString"
                }

                buffer.append(hexString)
            }

            return buffer.toString()
        }
    }
}