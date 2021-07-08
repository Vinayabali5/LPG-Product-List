package uk.ac.reigate.applications.repository

import org.springframework.data.repository.CrudRepository

import uk.ac.reigate.applications.domain.Category

interface CategoryRepository extends CrudRepository<Category, Integer> {
}
