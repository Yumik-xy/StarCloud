package top.yumik.core.common.canceljob

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

private val jobs = mutableMapOf<String, Job>()

/**
 * 可以自动取消的协程，使用 [label] 标记，如果有相同的 [label] 的协程在运行，会自动取消之前的协程
 *
 * @param label 协程标记
 * @param context 协程上下文
 * @param start 协程启动模式
 * @param block 协程体
 */
fun CoroutineScope.cancelableJob(
    label: String,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    jobs[label]?.cancel()
    jobs[label] = launch(context, start, block).apply {
        invokeOnCompletion {
            jobs.remove(label)
        }
    }
}

/**
 * 取消 [label] 标记的协程
 */
fun cancelJob(label: String) {
    jobs[label]?.cancel()
    jobs.remove(label)
}