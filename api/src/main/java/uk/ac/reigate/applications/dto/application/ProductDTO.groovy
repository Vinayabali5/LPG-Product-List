package uk.ac.reigate.applications.dto.application

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize

import uk.ac.reigate.applications.domain.Product

/**
 *
 * JSON serializable DTO containing Product data
 *
 */
@JsonSerialize
class ProductDTO implements Serializable {
	
	@JsonProperty
	Integer id
		
	@JsonProperty
	String name
	
	@JsonProperty
	String description
	
	@JsonProperty
	Integer categoryId
	
	@JsonProperty
	Date creationDate
	
	@JsonProperty
	Date updateDate
	
	@JsonProperty
	Date lastPurchasedDate
 
    @JsonProperty(value = "_categoryName")
    String categoryName
    
    /**
     * Default no-args constructor
     */
    ProductDTO() {}
    
	/**
	 * Constructor to create a ProductDto object from a Product object
	 *
	 * @param product the Product object to use for construction
	 */
    ProductDTO(Product product) {
		this.id = product.id
		this.categoryId = product?.category.id
        this.categoryName = product?.category?.name
		this.name = product?.name
		this.description = product?.description
		this.creationDate = product?.creationDate
		this.updateDate = product?.updateDate
		this.lastPurchasedDate = product?.lastPurchasedDate
    }
    
	/**
	 * This static method is used to create a ProductDto from a Product data object.
	 *
	 * @param product the Product data object to use for the creation.
	 * @return a ProductDto object based on the Product data object supplied.
	 */
	public static ProductDTO mapFromEntity(Product product) {
		return new ProductDTO(product);
	}
	
	/**
	 * This static method is used to create a List of ProductDto from a List of Product data object.
	 *
	 * @param products the List of Product data object to use for the creation.
	 * @return a List of ProductDto object based on the List of Product data object supplied.
	 */
    public static List<ProductDTO> mapFromList(List<Product> products) {
		return products.collect { Product product -> 
			new ProductDTO(product)
		}
	}
}
