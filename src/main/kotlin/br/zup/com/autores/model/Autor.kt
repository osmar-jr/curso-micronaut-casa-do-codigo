package br.zup.com.autores.model

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
class Autor(
        @field:NotBlank
        val nome: String,

        @field:NotBlank
        @field:Email
        @Column(unique = true, nullable = false)
        val email: String,

        @field:NotBlank
        val descricao: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val createdAt: LocalDateTime = LocalDateTime.now()

    override fun toString(): String {
        return "Autor(nome='$nome', email='$email', descricao='$descricao', id=$id, createdAt=$createdAt)"
    }


}