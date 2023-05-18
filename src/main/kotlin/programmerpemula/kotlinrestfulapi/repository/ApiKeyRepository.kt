package programmerpemula.kotlinrestfulapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import programmerpemula.kotlinrestfulapi.entity.ApiKey

interface ApiKeyRepository : JpaRepository<ApiKey, String> {

}