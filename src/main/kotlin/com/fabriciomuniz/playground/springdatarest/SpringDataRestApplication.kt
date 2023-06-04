package com.fabriciomuniz.playground.springdatarest

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource


@SpringBootApplication
class SpringDataRestApplication

fun main(args: Array<String>) {
    runApplication<SpringDataRestApplication>(*args)
}

@Entity
@Table(name = "avenger")
open class Avenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open val id: Long? = null

    @Column(name = "character", nullable = false)
    open val character: String? = null

    @Column(name = "name", nullable = false)
    open val name: String? = null
}

@RepositoryRestResource(collectionResourceRel = "avenger", path = "avengers")
interface AvengerRepository : PagingAndSortingRepository<Avenger, Long> {
    fun findByCharacter(character: String, pageable: Pageable): List<Avenger>
}