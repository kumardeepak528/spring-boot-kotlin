package com.books.demo.endpoint

import com.books.demo.model.Book
import com.books.demo.model.payload.CreateBookRequest
import com.books.demo.repository.BookRepository
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException

@RestController
class BookController(private val bookRepository: BookRepository) {


    @PostMapping("/book/add")
    fun addBook(@RequestBody bookRequest: CreateBookRequest): ResponseEntity<Book> {
        return ResponseEntity.ok().body(bookRepository.insert(Book(
            bookAuthor = bookRequest.bookAuthor,
            bookMrp = bookRequest.bookMrp,
            bookTitle = bookRequest.bookTitle
        )))
    }
    @GetMapping("/book/{id}")
    fun getBookDetailsById(@PathVariable("id") bookId : String): ResponseEntity<Book> {
        val book = bookRepository.findById(bookId)
        if(book.isEmpty) return ResponseEntity.noContent().build()

        return ResponseEntity.ok().body(book.get())
    }

    @GetMapping("/books")
    fun getAllPatients(): ResponseEntity<List<Book>> {
        return ResponseEntity.ok(bookRepository.findAll())
    }



    @PutMapping("/book")
    fun updateBook(@RequestBody bookRequest: CreateBookRequest): ResponseEntity<Book> {
        if(bookRequest._id==null) return ResponseEntity.badRequest().body(null)

        val bookToUpdate = bookRepository.findById(bookRequest._id)

        if(bookToUpdate.isEmpty) return ResponseEntity.noContent().build()

        bookToUpdate.get().apply {
            this.bookAuthor = bookRequest.bookAuthor
            this.bookMrp = bookRequest.bookMrp
            this.bookTitle = bookRequest.bookTitle
        }
        return ResponseEntity.ok().body(bookRepository.save(bookToUpdate.get()))
    }

    @DeleteMapping("/book/{id}")
    fun deleteBook(@PathVariable("id") bookId : String): ResponseEntity<Book> {
        bookRepository.deleteById(bookId)
        return ResponseEntity.ok().body(null)
    }


}

//C R U D