package programmerpemula.kotlinrestfulapi.auth

import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import programmerpemula.kotlinrestfulapi.error.UnauthourizedException
import programmerpemula.kotlinrestfulapi.repository.ApiKeyRepository
import java.lang.Exception


@Component
class ApiKeyInterceptor(val apiKeyRepository: ApiKeyRepository): WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val apiKey = request.getHeader("X-Api-Key")
        if(apiKey == null){
            throw UnauthourizedException()
        }

        if(!apiKeyRepository.existsById(apiKey)){
            throw UnauthourizedException()
        }
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
       // Nothing
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        // Nothing
    }
}