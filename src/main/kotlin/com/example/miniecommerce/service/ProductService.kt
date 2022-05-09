package com.example.miniecommerce.service

import com.example.miniecommerce.model.Product

interface ProductService {
    fun createProduct(product: Product):String
    fun getProducts():Iterable<Product>
}