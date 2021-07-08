package uk.ac.reigate.applications.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import uk.ac.reigate.applications.domain.Category
import uk.ac.reigate.applications.repository.CategoryRepository


@Service
class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepository
    
    /**
     * Default NoArgs constructor
     */
    CategoryService() {}
    
    /**
     * Autowired Constructor
     *
     * @param categoryRepository
     */
    CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    
    /**
     * Find an individual category using the categories ID fields
     *
     * @param id the ID fields to search for
     * @return the Category object that matches the ID supplied, or null if not found
     */
    Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
    
    /**
     * Find all categories
     *
     * @return a List of Categories
     */
    List<Category> findAll() {
        return categoryRepository.findAll();
    }
    
}
