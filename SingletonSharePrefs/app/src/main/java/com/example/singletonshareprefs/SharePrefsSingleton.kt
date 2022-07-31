package com.example.singletonshareprefs

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharePrefsSingleton private constructor() {

    companion object {
        private val sharePref = SharePrefsSingleton()
        private lateinit var sharedPreferences: SharedPreferences

        private const val IS_INSTALLED = "is_installed"

        fun getInstance(context: Context): SharePrefsSingleton {
            if (!::sharedPreferences.isInitialized) {
                synchronized(SharePrefsSingleton::class.java) {
                    if (!::sharedPreferences.isInitialized) {
                        sharedPreferences =
                            context.getSharedPreferences(context.packageName, MODE_PRIVATE)
                    }
                }
            }
            return sharePref
        }
    }

    val isInstalled: Boolean get() = sharedPreferences.getBoolean(IS_INSTALLED, false)

    fun putInstallState(isInstalled: Boolean) {
        sharedPreferences.edit().putBoolean(IS_INSTALLED, isInstalled).apply()
    }
}