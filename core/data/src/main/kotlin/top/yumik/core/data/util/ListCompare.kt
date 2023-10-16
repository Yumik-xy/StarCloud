package top.yumik.core.data.util

suspend fun <E> compare(
    oldList: List<E>,
    newList: List<E>,
    isItemSame: (oldItem: E, newItem: E) -> Boolean,
    isContentSame: (oldItem: E, newItem: E) -> Boolean,
    onUpsert: suspend (list: List<E>) -> Unit = {},
    onDelete: suspend (list: List<E>) -> Unit = {}
) {
    val oldSet = oldList.toSet()
    val newSet = newList.toSet()

    val upsert = newSet.filter { newItem ->
        oldSet.find { oldItem ->
            isItemSame(oldItem, newItem)
        }?.let { oldItem ->
            !isContentSame(oldItem, newItem)
        } ?: true
    }

    val delete = oldSet.filter { oldItem ->
        newSet.find { newItem ->
            isItemSame(oldItem, newItem)
        } == null
    }

    onUpsert(upsert.toList())
    onDelete(delete.toList())
}