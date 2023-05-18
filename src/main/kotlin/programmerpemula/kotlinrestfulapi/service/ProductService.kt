package programmerpemula.kotlinrestfulapi.service

import programmerpemula.kotlinrestfulapi.model.CreateProductRequest
import programmerpemula.kotlinrestfulapi.model.ListProductRequest
import programmerpemula.kotlinrestfulapi.model.ProductResponse
import programmerpemula.kotlinrestfulapi.model.UpdateProductRequest

interface ProductService  {

    fun create(createProductRequest: CreateProductRequest): ProductResponse

    fun get(id: String): ProductResponse

    fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse

    fun delete(id: String)

    fun list(listProductRequest: ListProductRequest): List<ProductResponse>

}