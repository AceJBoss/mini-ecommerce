package com.example.miniecommerce.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Service
class QueryDSLConfig {

   lateinit var elasticSearchOperations: ElasticsearchOperations
}