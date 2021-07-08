package uk.ac.reigate.applications.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import uk.ac.reigate.applications.api.IDTOToEntityConverter
import uk.ac.reigate.applications.domain.Product
import uk.ac.reigate.applications.dto.application.ProductDTO
import uk.ac.reigate.applications.repository.ProductRepository
import uk.ac.reigate.exceptions.InvalidDataException


@Service
class ProductService implements IDTOToEntityConverter<ProductDTO, Product> {

    @Autowired
    ProductRepository productRepository

    @Autowired
    CategoryService categoryService


    /**
     * Default NoArgs constructor
     */
    ProductService() {}

    /**
     * Autowired Constructor
     *
     * @param productRepository
     */
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Find an individual product using the products ID fields
     *
     * @param id the ID fields to search for
     * @return the Product object that matches the ID supplied, or null if not found
     */
    Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Find all products
     *
     * @return a SearchResult set with the list of Products
     */
    List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * This service method is used to save a complete Product object in the database
     *
     * @param product the new Product object to be saved
     * @return the saved version of the Product object
     */
    public Product save(Product product) {
        return productRepository.save(product)
    }

    /**
     * Saves a list of Product objects to the database
     *
     * @param products a list of Products to be saved to the database
     * @return the list of save Product objects
     */
    @Transactional
    public List<Product> saveProducts(List<Product> products) {
        return products.collect { product -> save(product) };
    }
	
	/**
	 * This method is used to create the new Product
	 * @param dto
	 * @return
	 */
	public Product createProduct(ProductDTO dto) {
		if (dto == null) {
			throw new InvalidDataException("Cannot create Product from null object.")
		}
		Product product = new Product()
		product.category = dto.categoryId != null ? categoryService.findById(dto.categoryId) : null
		product.name = dto.name
		product.description = dto.description
		product.creationDate = dto.creationDate
		product.updateDate = dto.updateDate
		product.lastPurchasedDate = dto.lastPurchasedDate
		return save(product)
	}

    /**
     * This method is used to convert a ProductDTO object into a Product entity.
     */
    public Product convertToEntity(ProductDTO dto) {
        Product product = new Product()
        if (dto.id != null) {
            product = findById(dto.id)
        }
        product.category = dto.categoryId != null ? categoryService.findById(dto.categoryId) : null
        product.name = dto.name
        product.description = dto.description
        product.creationDate = dto.creationDate
        product.updateDate = dto.updateDate
        product.lastPurchasedDate = dto.lastPurchasedDate

        return product
    }

    public List<Product> convertListToEntities(List<ProductDTO> products) {
        return products.collect { ProductDTO product ->
            convertToEntity(product)
        }
    }
}
