package uk.ac.reigate.applications.api;

import java.util.logging.Logger

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

import uk.ac.reigate.applications.domain.Product;
import uk.ac.reigate.applications.dto.application.ProductDTO
import uk.ac.reigate.applications.service.ProductService;
import uk.ac.reigate.exceptions.InvalidDataException
import uk.ac.reigate.exceptions.NotFoundException


/**
 * This class is used to set up a collection of Product HTTP end-points for the API. 
 * 
 * @author Vinaya Bali
 *
 */
@Controller
public class ProductsApi {
    
    private static final Logger LOGGER = Logger.getLogger(ProductsApi.class.getName());
    
    @Autowired
    private final ProductService productService;
	
    /**
     * Default NoArgs constructor
     */
    ProductsApi() {}
    
    /**
     * Autowired constructor
     */
    ProductsApi(ProductService productService) {
        this.productService = productService;
    }
    
    /**
     * This method is used to retrieve all the Product objects and send to the user as ProductDTO objects.
     *
     * @return A ResponseEntity with the corresponding list of ProductDto objects
     * @throws NotFoundException if nothing is found then the the NotFoundException is thrown.
     */
    @CrossOrigin("*")
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAll() throws NotFoundException {
        LOGGER.info("** ProductssApi - productsGet");
        List<Product> products = productService.findAll();
        return new ResponseEntity<List<ProductDTO>>(ProductDTO.mapFromList(products), HttpStatus.OK);
    }
	
	/**
	 * This method is used to create the new product to the database.
	 * 
	 * @param productDto
	 * @return
	 */
	@CrossOrigin("*")
	@PostMapping("/product")
	ResponseEntity<ProductDTO> createApplication(@RequestBody(required = true) ProductDTO productDto) {
		// Verify that data sent does not have an ID
		if (productDto.id != null) throw new InvalidDataException("You cannot create a new product if an ID fields has been supplied. Use PUT /update for updating the product.")
		// Create and save the product
		Product product = productService.createProduct(productDto)
		// Send Response
		return new ResponseEntity<ProductDTO>(new ProductDTO(product), HttpStatus.CREATED)
	}
	
}
