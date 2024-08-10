package com.example.fe_app_android_b2b_poc.utils

import android.content.Context
import com.example.fe_app_android_b2b_poc.utils.Constants.PREFS_TOKEN_FILE
import com.example.fe_app_android_b2b_poc.utils.Constants.USER_ACCESS_TOKEN
import com.example.fe_app_android_b2b_poc.utils.Constants.USER_REFRESH_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private var prefs = context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveAccessToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_ACCESS_TOKEN, token)
        editor.apply()
    }

    fun getAccessToken(): String? {
        return prefs.getString(USER_ACCESS_TOKEN, null)
    }

    fun saveRefreshToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_REFRESH_TOKEN, token)
        editor.apply()
    }

    fun getRefreshToken(): String? {
        return prefs.getString(USER_REFRESH_TOKEN, null)
    }

    fun clearAccessToken(){
        val editor = prefs.edit()
        editor.remove(USER_ACCESS_TOKEN)
        editor.apply()
    }

    fun clearRefreshToken(){
        val editor = prefs.edit()
        editor.remove(USER_REFRESH_TOKEN)
        editor.apply()
    }
}