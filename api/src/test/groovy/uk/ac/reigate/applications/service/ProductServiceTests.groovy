package uk.ac.reigate.applications.service

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

import java.text.SimpleDateFormat

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

import uk.ac.reigate.applications.domain.Category
import uk.ac.reigate.applications.domain.Product
import uk.ac.reigate.applications.dto.application.ProductDTO
import uk.ac.reigate.applications.repository.ProductRepository

@SpringBootTest
class ProductServiceTests {

	@MockBean
	ProductRepository productRepository

	@MockBean
	CategoryService categoryService


	@Autowired
	ProductService productService

    // Sample Data
    Category sampleCategory = new Category(id:1, name: 'Kitchen')
    Product sampleProduct1 = new Product(id: 1, category: new Category(id: 1, name:'kitchen'), name: 'Kitchen Set', description: 'kitchen knife', creationDate : new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'), updateDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'), lastPurchasedDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/10/01'))
    Product sampleProduct2 = new Product(id: 2, category: new Category(id: 1, name:'washroom'), name: 'Kitchen Set', description: 'kitchen knife', creationDate : new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'), updateDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'), lastPurchasedDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/10/01'))
    
    @BeforeEach
	public void setup() {
        when(productRepository.findById(1)).thenReturn(new Optional(sampleProduct1))
        when(productRepository.findAll()).thenReturn(new ArrayList([sampleProduct1, sampleProduct2]))
	}

	ProductDTO sampleDTO() {
		return new ProductDTO(
				id: 1,
				categoryId: 1,
				name: 'Kitchen',
				description: 'Knife Set',
				creationDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'),
				updateDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'),
				lastPurchasedDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01')
				)
	}
    
    @Test 
    public void testFindById() {
        Product entity = productService.findById(sampleProduct1.id)
        
        verify(productRepository, times(1)).findById(sampleProduct1.id)
        
        assertEquals(entity.category, sampleProduct1.category)
        assertEquals(entity.name, sampleProduct1.name)
        assertEquals(entity.description, sampleProduct1.description)
        assertEquals(entity.creationDate, sampleProduct1.creationDate)
        assertEquals(entity.updateDate, sampleProduct1.updateDate)
    }

    @Test 
    public void testFindById_invalidId() {
        Product entity = productService.findById(1)
        
        verify(productRepository, times(1)).findById(1)

    }

    @Test 
    public void testFindAll() {
        List<Product> list = productService.findAll()
        
        verify(productRepository, times(1)).findAll()
        
        assertEquals(list.empty, false)
        assertEquals(list[0].category, sampleProduct1.category)
        assertEquals(list[0].name, sampleProduct1.name)
        assertEquals(list[0].description, sampleProduct1.description)
        assertEquals(list[0].creationDate, sampleProduct1.creationDate)
        assertEquals(list[0].updateDate, sampleProduct1.updateDate)
    }

}
