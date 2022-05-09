package com.example.miniecommerce.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName="products" )
data class Product(
    @Id
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val ratings: Double
)