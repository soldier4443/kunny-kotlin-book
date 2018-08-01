package com.androidhuman.example.simplegithub.data

import android.content.Context
import android.preference.PreferenceManager

class AuthTokenProvider(private val context: Context) {

    val token: String?
        get() = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_AUTH_TOKEN, null)

    fun updateToken(token: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putString(KEY_AUTH_TOKEN, token)
            .apply()
    }

    companion object {
        // const를 사용하면 정적 필드처럼 취급됨.
        private const val KEY_AUTH_TOKEN = "auth_token"
    }
}
