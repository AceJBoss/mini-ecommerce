package com.example.miniecommerce.repository

import com.example.miniecommerce.model.Product
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository


@Repository
interface ProductRepository : ElasticsearchRepository<Product, Int> {

    fun findByName(name: String?): List<Product?>?

    fun findByNameContaining(name: String?): List<Product?>?

    fun findByManufacturerAndCategory(manufacturer: String?, category: String?): List<Product?>?

}