package co.karpenko.cats.data.mapper

import co.karpenko.cats.data.entity.CatModel

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

object CatMapper {
    fun toCat(catModel: CatModel): Cat =
        Cat(
            id = catModel.id,
            name = catModel.name,
            description = catModel.description,
            countryCode = catModel.countryCode,
            temperament = catModel.temperament,
            linkWikipedia = catModel.wikipediaUrl,
            linkImage = catModel.image?.url,
        )
}
