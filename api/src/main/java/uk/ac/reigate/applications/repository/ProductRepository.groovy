package uk.ac.reigate.applications.repository

import org.springframework.data.repository.CrudRepository

import uk.ac.reigate.applications.domain.Product

interface ProductRepository extends CrudRepository<Product, Integer> {
}
