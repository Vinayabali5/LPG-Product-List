package uk.ac.reigate.applications.dto.application;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import groovy.transform.EqualsAndHashCode;
import uk.ac.reigate.applications.domain.Category

/**
 *
 * JSON serializable DTO containing Category data
 *
 */
@JsonSerialize
@EqualsAndHashCode(includeFields=true)
public class CategoryDTO implements Serializable {
    
    @JsonProperty
    private Integer id;
    
    @JsonProperty
    private String name;

    
    /**
     * Default No Args constructor
     */
    public CategoryDTO() {
    }
    
    /**
     * Constructor to create a CategoryDto object from a Category object
     *
     * @param category the Category object to use for construction
     */
    CategoryDTO(Category category) {
        this.id = category.id;
        this.name = category.name;
    }
    
    @Override
    public String toString() {
        return "CategoryDto [id=" + id + ", name=" + name + "]";
    }
    
	/**
	 * This static method is used to create a CategoryDto from a Category data object.
	 *
	 * @param category the Category data object to use for the creation.
	 * @return a CategoryDto object based on the Category data object supplied.
	 */
    public static CategoryDTO mapFromEntity(Category category) {
        return new CategoryDTO(category);
    }
    
	/**
	 * This static method is used to create a List of CategoryDto from a List of Category data object.
	 *
	 * @param categories the List of Category data object to use for the creation.
	 * @return a List of CategoryDto object based on the List of Category data object supplied.
	 */
    public static List<CategoryDTO> mapFromList(List<Category> categories) {
        return categories.collect { category ->  new CategoryDTO(category) };
    }
}
