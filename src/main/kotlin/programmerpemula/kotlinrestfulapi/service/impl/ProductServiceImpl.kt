package programmerpemula.kotlinrestfulapi.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmerpemula.kotlinrestfulapi.entity.Product
import programmerpemula.kotlinrestfulapi.error.NotFoundException
import programmerpemula.kotlinrestfulapi.model.CreateProductRequest
import programmerpemula.kotlinrestfulapi.model.ListProductRequest
import programmerpemula.kotlinrestfulapi.model.ProductResponse
import programmerpemula.kotlinrestfulapi.model.UpdateProductRequest
import programmerpemula.kotlinrestfulapi.repository.ProductRepository
import programmerpemula.kotlinrestfulapi.service.ProductService
import programmerpemula.kotlinrestfulapi.validation.ValidationUtil
import java.util.*
import java.util.stream.Collectors


@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil : ValidationUtil
) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {

      validationUtil.validate(
          createProductRequest
      )
      val product = Product(
              id = createProductRequest.id!!,
              name = createProductRequest.name!!,
              price = createProductRequest.price!!,
              quantity = createProductRequest.quantity!!,
              createdAt = Date(),
              updatedAt = null
      )

       productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = findProductByIdOrThroeNotFound(id)

            return convertProductToProductResponse(product)

    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findProductByIdOrThroeNotFound(id)

        validationUtil.validate(updateProductRequest)

        product.apply{
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updatedAt = Date()

        }

        productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = findProductByIdOrThroeNotFound(id)
        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
      val page =  productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val products:List<Product> =  page.get().collect(Collectors.toList())

        return products.map{convertProductToProductResponse(it)}
    }


    private fun findProductByIdOrThroeNotFound(id: String): Product {
        val product = productRepository.findByIdOrNull(id)
        if (product == null){
            throw NotFoundException()
        }else {
            return product
        }
    }

    private fun convertProductToProductResponse(product: Product ): ProductResponse{
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }
}