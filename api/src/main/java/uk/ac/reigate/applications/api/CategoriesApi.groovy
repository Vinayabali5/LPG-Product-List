package uk.ac.reigate.applications.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import uk.ac.reigate.applications.dto.application.CategoryDTO;
import uk.ac.reigate.applications.service.CategoryService;
import uk.ac.reigate.exceptions.NotFoundException;
import uk.ac.reigate.applications.domain.Category;

/**
 * This class is used to set up a collection of Category HTTP end-points for the API. 
 * 
 * @author Vinaya Bali
 *
 */
@Controller
public class CategoriesApi {
    
    
    @Autowired
    private final CategoryService categoryService;
	
    /**
     * Default NoArgs constructor
     */
    CategoriesApi() {}
    
    /**
     * Autowired constructor
     */
    CategoriesApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    /**
     * This method is used to retrieve all the Category objects and send to the user as CategoryDTO objects.
     *
     * @return A ResponseEntity with the corresponding list of CategoryDto objects
     * @throws NotFoundException if nothing is found then the the NotFoundException is thrown.
     */
    @CrossOrigin("*")
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAll() throws NotFoundException {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<List<CategoryDTO>>(CategoryDTO.mapFromList(categories), HttpStatus.OK);
    }
	
}
