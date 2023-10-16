package top.yumik.core.common.appcontext

import android.content.Context
import androidx.startup.Initializer

internal lateinit var appContext: Context

class AppContextInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        appContext = context
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}