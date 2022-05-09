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
            Product(100, "Laptop", "Just Laptop", 35000.0, 4.5, "Technology", "Dell"),
            Product(101, "Mouse", "Just Mouse", 5000.0, 3.5, "Technology", "Hp"),
            Product(102, "iPhone", "Just iPhone", 90000.0, 4.7, "Technology","Apple"),
            Product(103, "Bag", "Just Bag", 12000.0, 4.2, "Household","Gucci")
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

    override fun findByName(name: String?): List<Product?>? {
        TODO("Not yet implemented")
        return productRepository.findByName(name)
    }

    override fun findByNameContaining(name: String?): List<Product?>? {
        TODO("Not yet implemented")
        return productRepository.findByNameContaining(name)
    }

    override fun findByManufacturerAndCategory(manufacturer: String?, category: String?): List<Product?>? {
        TODO("Not yet implemented")
        return productRepository.findByManufacturerAndCategory(manufacturer,category)
    }


}