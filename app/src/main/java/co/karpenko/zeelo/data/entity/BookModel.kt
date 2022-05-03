package co.karpenko.zeelo.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Alexander Karpenko on 02/05/22.
 * java.karpenko@gmail.com
 */

data class BookModel(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("image") var image: String?,
    @SerializedName("price") var price: String?,
)
