package br.zup.com.autores

import br.zup.com.autores.model.Autor
import br.zup.com.autores.repository.AutorRepository
import br.zup.com.autores.request.NovoAutorRequest
import br.zup.com.autores.response.AutorDetalheResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/api/autores")
class NovoAutorController(val autorRepository: AutorRepository) {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass.name)

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {
        val autor: Autor = request.toAutor()
        autorRepository.save(autor)

        logger.info(autor.toString())
        val uri = UriBuilder.of("/api/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(AutorDetalheResponse(autor), uri)
    }

    @Get
    fun lista(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if (email.isNotBlank()){

            val optionalAutor: Optional<Autor> = autorRepository.findByEmail(email)
            if (optionalAutor.isEmpty) return HttpResponse.notFound("Autor não encontrado.")

            return HttpResponse.ok(AutorDetalheResponse(optionalAutor.get()))
        }

        val autores: List<Autor> = autorRepository.findAll()
        val autoresResponse = autores.map { autor -> AutorDetalheResponse(autor) }

        return HttpResponse.ok(autoresResponse)
    }

    @Get("/{autorId}")
    @Produces(MediaType.TEXT_HTML)
    fun detalha(@PathVariable("autorId") autorId: Long): HttpResponse<Any> {

        val optionalAutor: Optional<Autor> = autorRepository.findById(autorId)
        if (optionalAutor.isEmpty) return HttpResponse.notFound("Autor não foi encontrado.")
        val autorDetalheResponse = AutorDetalheResponse(optionalAutor.get())
        return HttpResponse.ok(autorDetalheResponse)
    }
}