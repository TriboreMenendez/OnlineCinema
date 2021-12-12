package tribore.onlinecinema.util

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: String): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
    val formatedDate = formatter.parse(date)
    return SimpleDateFormat("yyyy", Locale.ROOT).format(formatedDate ?: "")
}