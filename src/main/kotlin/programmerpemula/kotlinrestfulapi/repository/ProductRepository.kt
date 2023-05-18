package programmerpemula.kotlinrestfulapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import programmerpemula.kotlinrestfulapi.entity.Product

interface ProductRepository : JpaRepository<Product, String> {
}