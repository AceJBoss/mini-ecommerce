package com.example.miniecommerce.service

import com.example.miniecommerce.model.Product
import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHit
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.*
import org.springframework.stereotype.Service


@Service
class ProductSearchService(private val elasticsearchOperations: ElasticsearchOperations) {

    companion object {
        private const val PRODUCT_INDEX = "products"
    }

    fun findProductsByBrand(brandName: String?) {
        val queryBuilder: QueryBuilder = QueryBuilders
            .matchQuery("manufacturer", brandName)
        // .fuzziness(0.8)
        // .boost(1.0f)
        // .prefixLength(0)
        // .fuzzyTranspositions(true);
        val searchQuery: Query = NativeSearchQueryBuilder()
            .withQuery(queryBuilder)
            .build()
        val productHits: SearchHits<Product> = elasticsearchOperations
            .search(
                searchQuery, Product::class.java,
                IndexCoordinates.of(PRODUCT_INDEX)
            )
        println("productHits ${productHits.getSearchHits().size}, ${productHits.getSearchHits()}")
        val searchHits: List<SearchHit<Product?>> = productHits.getSearchHits()
        val i = 0
        for (searchHit in searchHits) {
            println("searchHit ${searchHit}")
        }
    }

    fun findByProductName(productName: String) {
        val searchQuery: Query = StringQuery(
            "{\"match\":{\"name\":{\"query\":\"$productName\"}}}\""
        )
        val products: SearchHits<Product> = elasticsearchOperations.search(
            searchQuery, Product::class.java,
            IndexCoordinates.of(PRODUCT_INDEX)
        )
    }

    fun findByProductPrice(productPrice: String?) {
        val criteria: Criteria = Criteria("price").greaterThan(10.0).lessThan(100.0)
        val searchQuery: Query = CriteriaQuery(criteria)
        val products: SearchHits<Product> = elasticsearchOperations.search(
            searchQuery, Product::class.java,
            IndexCoordinates.of(PRODUCT_INDEX)
        )
    }

    fun processSearch(query: String?): List<Product> {
        println("Search with query ${query}")

        // 1. Create query on multiple fields enabling fuzzy search
        val queryBuilder: QueryBuilder = QueryBuilders
            .multiMatchQuery(query, "name", "description")
            .fuzziness(Fuzziness.AUTO)
        val searchQuery: Query = NativeSearchQueryBuilder()
            .withFilter(queryBuilder)
            .build()

        // 2. Execute search
        val productHits: SearchHits<Product> = elasticsearchOperations
            .search(
                searchQuery, Product::class.java,
                IndexCoordinates.of(PRODUCT_INDEX)
            )

        // 3. Map searchHits to product list
        val productMatches: MutableList<Product> = ArrayList()
        productHits.forEach { srchHit -> productMatches.add(srchHit.getContent()) }
        return productMatches
    }

    fun fetchSuggestions(query: String): List<String> {
        val queryBuilder: QueryBuilder = QueryBuilders
            .wildcardQuery("name", "$query*")
        val searchQuery: Query = NativeSearchQueryBuilder()
            .withFilter(queryBuilder)
            .withPageable(PageRequest.of(0, 5))
            .build()
        val searchSuggestions: SearchHits<Product> = elasticsearchOperations.search(
            searchQuery,
            Product::class.java,
            IndexCoordinates.of(PRODUCT_INDEX)
        )
        val suggestions: MutableList<String> = ArrayList()
        searchSuggestions.getSearchHits().forEach { searchHit -> suggestions.add(searchHit.getContent().name) }
        return suggestions
    }

}