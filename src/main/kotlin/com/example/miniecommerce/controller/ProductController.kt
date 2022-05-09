package com.example.miniecommerce.controller

import com.example.miniecommerce.model.Product
import com.example.miniecommerce.service.Impl.ProductServiceImpl
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product/")
class ProductController(private val productServiceImpl: ProductServiceImpl) {

    @PostMapping("create")
    fun createProduct(@RequestBody product: Product): String{
        productServiceImpl.createProduct(product)
        return "Product Saved Successfully"
    }

    @GetMapping("fetch")
    fun getProducts():Iterable<Product>{
        return productServiceImpl.getProducts()
    }


}