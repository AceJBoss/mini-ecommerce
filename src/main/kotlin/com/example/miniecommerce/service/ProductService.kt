package com.example.miniecommerce.service

import com.example.miniecommerce.model.Product


interface ProductService {
    fun createProduct(product: Product):String

    fun getProducts():List<Product>

    fun findByName(name: String?): List<Product?>?

    fun findByNameContaining(name: String?): List<Product?>?

    fun findByManufacturerAndCategory(manufacturer: String?, category: String?): List<Product?>?
}