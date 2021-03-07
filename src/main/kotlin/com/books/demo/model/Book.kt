package com.books.demo.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Book (

    @JsonSerialize(using = ToStringSerializer::class)
    @Id
    val _id : ObjectId =ObjectId.get(),

    var bookTitle : String,
    var bookAuthor : String,
    var bookMrp : Double,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val modifiedDate: LocalDateTime = LocalDateTime.now())