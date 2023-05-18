package programmerpemula.kotlinrestfulapi.controller

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import programmerpemula.kotlinrestfulapi.error.NotFoundException
import programmerpemula.kotlinrestfulapi.error.UnauthourizedException
import programmerpemula.kotlinrestfulapi.model.WebResponse
import javax.validation.ConstraintViolationException


@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException ): WebResponse<String>{
        return WebResponse (
            code = 400,
            status = "BAD REQUEST",
            data = constraintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(notFoundException: NotFoundException): WebResponse<String>{
        return WebResponse(
            code = 404,
            status = "NOT FOUND",
            data = "Not Found"
        )
    }

    @ExceptionHandler(value = [UnauthourizedException::class])
    fun unauthourized(unauthourizedException: UnauthourizedException): WebResponse<String>{
        return WebResponse(
            code = 401,
            status = "UNAUTHORIZED",
            data = "Please put your X-Api-Key"
        )
    }
}