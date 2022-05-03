package co.karpenko.zeelo.data

import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * Created by Alexander Karpenko on 2/5/22.
 * java.karpenko@gmail.com
 */
class Converters {

    @TypeConverter
    fun listToJson(value: List<Int>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()
}
