package com.androidhuman.example.simplegithub.data

import android.content.Context
import android.preference.PreferenceManager

class AuthTokenProvider(private val context: Context) {

    var token: String?
        get() = pref().getString(KEY_AUTH_TOKEN, null)
        set(token) = pref().edit()
            .putString(KEY_AUTH_TOKEN, token)
            .apply()

    private fun pref() = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        // const를 사용하면 정적 필드처럼 취급됨.
        private const val KEY_AUTH_TOKEN = "auth_token"
    }

}
