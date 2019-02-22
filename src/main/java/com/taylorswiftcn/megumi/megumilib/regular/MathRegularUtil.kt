package com.taylorswiftcn.megumi.megumilib.regular

class MathRegularUtil {
    companion object {

        fun isInt(string: String): Boolean {
            return string.matches("^-?\\d+\$".toRegex())
        }

        fun isPositiveInt(string: String): Boolean {
            return string.matches("^[0-9]*[1-9][0-9]*\$".toRegex())
        }

        fun isNegativeInt(string: String): Boolean {
            return string.matches("^-[0-9]*[1-9][0-9]*\$".toRegex())
        }

        fun isNaturalNumber(string: String): Boolean {
            return string.matches("^\\d+\$".toRegex())
        }

        fun isFloat(string: String): Boolean {
            return string.matches("^(-?\\d+)(\\.\\d+)?\$".toRegex())
        }

        fun isPositiveFloat(string: String): Boolean {
            return string.matches("^(([0-9]+\\.[0-9]*[1-9][0-9]*) ?([0-9]*[1-9][0-9]*\\.[0-9]+) ?([0-9]*[1-9][0-9]*))\$".toRegex())
        }

        fun isNegativeFloat(string: String): Boolean {
            return string.matches("^(-(([0-9]+\\.[0-9]*[1-9][0-9]*) ?([0-9]*[1-9][0-9]*\\.[0-9]+) ?([0-9]*[1-9][0-9]*)))\$".toRegex())
        }
    }
}