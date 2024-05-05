package com.example.pokeapp.core.util

class StringUtils {

    companion object {
        fun returnOffset(nextUrl: String): String {
            val afterOffsetTitle = returnOffsetAndLimit(nextUrl).substringAfter("offset=")
            return afterOffsetTitle.substringBefore("&")
        }

        fun returnLimit(nextUrl: String): String {
            return nextUrl.substringAfter("limit=")
        }

        private fun returnOffsetAndLimit(nextUrl: String): String {
            return nextUrl.substringAfterLast("?")
        }
    }
}