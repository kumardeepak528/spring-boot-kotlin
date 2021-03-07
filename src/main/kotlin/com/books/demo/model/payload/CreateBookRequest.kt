package com.books.demo.model.payload

data class CreateBookRequest (
    val _id : String?,
    val bookTitle : String,
    val bookAuthor : String,
    val bookMrp : Double)