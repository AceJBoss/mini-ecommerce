package com.example.miniecommerce.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import javax.annotation.Generated

@Document(indexName="products" )
data class Product(
    @Id
    @Generated
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val ratings: Double,
    val category: String,
    val manufacturer: String
)