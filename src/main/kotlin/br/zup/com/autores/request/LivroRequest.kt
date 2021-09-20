package br.zup.com.autores.request

import io.micronaut.core.annotation.Introspected
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class LivroRequest(@field:NotBlank val titulo: String,
                        @field:NotBlank val isbn: String,
                        @field:NotNull val dataDePublicacao: LocalDate)