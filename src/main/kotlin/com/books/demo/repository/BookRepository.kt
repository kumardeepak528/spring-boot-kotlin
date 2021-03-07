package com.books.demo.repository

import com.books.demo.model.Book
import org.springframework.data.mongodb.repository.MongoRepository

interface BookRepository :MongoRepository<Book,String>{
}