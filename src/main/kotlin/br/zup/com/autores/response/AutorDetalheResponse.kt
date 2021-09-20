package br.zup.com.autores.response

import br.zup.com.autores.model.Autor
import com.sun.istack.NotNull
import io.micronaut.core.annotation.Introspected
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Introspected
data class AutorDetalheResponse(
    @field:NotBlank
    val nome: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    val descricao: String
){
    constructor(@NotNull @Valid autor: Autor): this(
        nome = autor.nome,
        email = autor.email,
        descricao = autor.descricao
    )
}
