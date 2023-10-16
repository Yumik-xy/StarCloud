package top.yumik.core.common.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import top.yumik.core.common.appcontext.appContext

fun startBrowser(url: String, context: Context = appContext) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    ContextCompat.startActivity(context, intent, null)
}