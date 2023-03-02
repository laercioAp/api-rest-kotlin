package com.example.apirest.controller

import com.example.apirest.entities.Contact
import com.example.apirest.repositories.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException
import javax.validation.Valid

@RestController
@RequestMapping("/contacts")
class ContactController {

    @Autowired
    lateinit var repository: ContactRepository

    @GetMapping
    fun listarContatos(): List<Contact> {
        return repository.findAll()
    }

    @PostMapping
    fun createContato(@Valid @RequestBody contact: Contact) : Contact {
        return repository.save(contact)
    }

    @GetMapping("/{id}")
    fun show (@PathVariable("id") id: Long) : Contact {
        return repository.findById(id).orElseThrow { EntityNotFoundException() }
    }

    @PutMapping("/{id}")
    fun updateContato(@Valid @PathVariable("id") id: Long, @RequestBody newContact: Contact): Contact {
        val contact = repository.findById(id).orElseThrow { EntityNotFoundException() }

        contact.apply {
            this.name = newContact.name
            this.email = newContact.email
        }

        return  repository.save(contact)
    }

    @DeleteMapping("/{id}")
    fun deletedContato(@PathVariable("id") id: Long) {
        val contact = repository.findById(id).orElseThrow { EntityNotFoundException() }

        return  repository.delete(contact)
    }
}