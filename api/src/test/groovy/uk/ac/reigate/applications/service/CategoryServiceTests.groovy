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
import uk.ac.reigate.applications.dto.application.CategoryDTO
import uk.ac.reigate.applications.repository.CategoryRepository

@SpringBootTest
class CategoryServiceTests {

	@MockBean
	CategoryRepository categoryRepository

	@Autowired
	CategoryService categoryService

    // Sample Data
    Category sampleCategory1 = new Category(id:1, name: 'Kitchen')
	Category sampleCategory2 = new Category(id:1, name: 'washroom')
    
    
    @BeforeEach
	public void setup() {
        when(categoryRepository.findById(1)).thenReturn(new Optional(sampleCategory1))
        when(categoryRepository.findAll()).thenReturn(new ArrayList([sampleCategory1, sampleCategory2]))
	}

	CategoryDTO sampleDTO() {
		return new CategoryDTO(
				id: 1,
				name: 'Kitchen'				
				)
	}
    
    @Test 
    public void testFindById() {
        Category entity = categoryService.findById(sampleCategory1.id)
        
        verify(categoryRepository, times(1)).findById(sampleCategory1.id)
        
        assertEquals(entity.name, sampleCategory1.name)
    }

    @Test 
    public void testFindById_invalidId() {
        Category entity = categoryService.findById(1)
        
        verify(categoryRepository, times(1)).findById(1)

    }

    @Test 
    public void testFindAll() {
        List<Category> list = categoryService.findAll()        
        verify(categoryRepository, times(1)).findAll()       
        assertEquals(list.empty, false)
        assertEquals(list[0].name, sampleCategory1.name)
    }

}
