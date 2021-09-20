package br.zup.com.autores

import br.zup.com.autores.model.Autor
import br.zup.com.autores.repository.AutorRepository
import br.zup.com.autores.response.AutorDetalheResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
internal class NovoAutorControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    fun setUp() {

        autor = Autor(
            nome = "Autor de Teste",
            email = "teste@autores.com.br",
            descricao = "Autor de Teste Micronaut"
        )

        autorRepository.save(autor)
    }

    @AfterEach
    fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve retornar um autor quando email valido informado`(){
        val response = client
            .toBlocking()
            .exchange("/api/autores?email=${autor.email}", AutorDetalheResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.descricao, response.body()!!.descricao)
        assertEquals(autor.email, response.body()!!.email)
    }

}