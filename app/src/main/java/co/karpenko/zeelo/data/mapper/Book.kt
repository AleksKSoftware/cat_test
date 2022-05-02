package co.karpenko.zeelo.data.mapper

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Alexander Karpenko on 02/05/22.
 * java.karpenko@gmail.com
 */

@Entity(tableName = "books")
@Parcelize
data class Book(
    @PrimaryKey
    val id: Int,
    val title: String?,
    val image: String? = "",
    val author: String?,
    val price: String?,
) : Parcelable

