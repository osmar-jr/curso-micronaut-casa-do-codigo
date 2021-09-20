package br.zup.com.autores.request

import br.zup.com.autores.model.Autor
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Introspected
data class NovoAutorRequest(@field:NotBlank val nome: String,
                            @field:NotBlank @field:Email val email: String,
                            @field:NotBlank val descricao: String) {
    fun toAutor(): Autor {
        return Autor(nome = nome, email = email, descricao = descricao)
    }
}
