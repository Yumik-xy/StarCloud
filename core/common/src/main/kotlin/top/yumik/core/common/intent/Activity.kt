package top.yumik.core.common.intent

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import top.yumik.core.common.appcontext.appContext

fun <T : Activity> startActivity(targetActivityClass: Class<T>, context: Context = appContext) {
    val intent = Intent(context, targetActivityClass)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    ContextCompat.startActivity(context, intent, null)
}

inline fun <reified T : Activity> startActivity(context: Context) {
    startActivity(T::class.java, context)
}

inline fun <reified T : Activity> startActivity() {
    startActivity(T::class.java)
}