package com.example.miniecommerce.controller

import com.example.miniecommerce.model.Product
import com.example.miniecommerce.service.Impl.ProductServiceImpl
import com.example.miniecommerce.service.ProductSearchService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/")
class ProductController(private val productServiceImpl: ProductServiceImpl, private val searchService: ProductSearchService) {

    @PostMapping("create")
    fun createProduct(@RequestBody product: Product): String{
        productServiceImpl.createProduct(product)
        return "Product Saved Successfully"
    }

    @GetMapping("fetch")
    fun getProducts():Iterable<Product>{
        return productServiceImpl.getProducts()
    }

    @GetMapping("fetchByName")
    fun fetchByName(@RequestParam(value = "q", required = false) query: String):List<Product?>?{
        return productServiceImpl.findByName(query)
    }

    @GetMapping("fetchByNameContaining")
    fun fetchByNameContaining(@RequestParam(value = "q", required = false) query: String):List<Product?>?{
        return productServiceImpl.findByNameContaining(query)
    }

    @GetMapping("products")
    fun fetchByNameOrDesc(@RequestParam(value = "q", required = false) query: String?): List<Product?>? {
        val products: List<Product?> = searchService.processSearch(query)
        return products
    }

    @GetMapping("suggestions")
    fun fetchSuggestions(@RequestParam(value = "q", required = false) query: String): List<String?>? {
        val suggests: List<String?> = searchService.fetchSuggestions(query)
        return suggests
    }







}