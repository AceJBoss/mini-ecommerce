package com.example.miniecommerce.service.Impl

import com.example.miniecommerce.model.Product
import com.example.miniecommerce.repository.ProductRepository
import com.example.miniecommerce.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class ProductServiceImpl : ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    @PostConstruct
    fun initDB(){
        val productList : List<Product> = listOf(
            Product(100, "Laptop", "Just Laptop", 35000.0, 4.5),
            Product(101, "Mouse", "Just Mouse", 5000.0, 3.5),
            Product(102, "iPhone", "Just iPhone", 90000.0, 4.7)
        )
        productRepository.saveAll(productList)
    }
    override fun createProduct(product: Product): String {
        TODO("Not yet implemented")
        productRepository.save(product)
        return "Product Saved Successfully"
    }

    override fun getProducts(): Iterable<Product> {
        TODO("Not yet implemented")
        return productRepository.findAll()
    }
}