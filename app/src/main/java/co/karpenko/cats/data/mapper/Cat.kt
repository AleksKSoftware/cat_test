package co.karpenko.cats.data.mapper

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

@Entity(tableName = "cat")
data class Cat(
    @PrimaryKey
    val id: String,
    val name: String?,
    val description: String? = "",
    val countryCode: String?,
    val temperament: String?,
    val linkWikipedia: String?,
    val linkImage: String?,
)
