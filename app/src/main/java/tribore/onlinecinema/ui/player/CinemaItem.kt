package tribore.onlinecinema.ui.player

import android.util.Log

object CinemaItem {

    var newInstance: Boolean = true
    var selectedItemCinema: String = "2"

    fun setItem(item: String) {
        if (item != selectedItemCinema) {
            newInstance = true
            selectedItemCinema = item
            Log.d("mylog", "set item")
        }
        else newInstance = false
    }

    fun getItem(): String {
        Log.d("mylog", "get item")
        return selectedItemCinema
    }

}