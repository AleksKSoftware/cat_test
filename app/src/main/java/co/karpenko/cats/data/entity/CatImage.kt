package co.karpenko.cats.data.entity

import com.google.gson.annotations.SerializedName


data class CatImage(
    @SerializedName("id") var id: String?,
    @SerializedName("width") var width: Int?,
    @SerializedName("height") var height: Int?,
    @SerializedName("url") var url: String?,
)
