package co.karpenko.cats.data.entity

import com.google.gson.annotations.SerializedName


data class CatWeight(
  @SerializedName("imperial") var imperial: String?,
  @SerializedName("metric") var metric: String?,
)