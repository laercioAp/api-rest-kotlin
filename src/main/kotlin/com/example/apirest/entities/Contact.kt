package com.example.apirest.entities

import org.jetbrains.annotations.NotNull
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Entity
@Table(name="contacts")
class Contact(
    @field:Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   var id: Long,

    @field:NotNull
    @field:Size(min = 5, max = 50, message = "O nome deve possuir até 50 caracteres")
   var name: String,

   @field: NotNull
   @field: Email(message = "O email deve ser valido")
   var email: String


)