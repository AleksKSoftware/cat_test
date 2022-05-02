package co.karpenko.zeelo.data.mapper

import co.karpenko.zeelo.data.entity.BookModel

/**
 * Created by Alexander Karpenko on 02/05/22.
 * java.karpenko@gmail.com
 */

object BookMapper {
    fun toBook(bookModel: BookModel): Book =
        Book(
            id = bookModel.id,
            author = bookModel.name,
            title = bookModel.title,
            price = bookModel.price,
            image = bookModel.image,
        )

    fun toBookModel(book: Book): BookModel =
        BookModel(
            id = book.id,
            name = book.author,
            title = book.title,
            price = book.price,
            image = book.image,
        )
}