package uk.ac.reigate.applications.dto

import static org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

import uk.ac.reigate.applications.domain.Category
import uk.ac.reigate.applications.dto.application.CategoryDTO

class CategoryDTOTests {

	@Test
	public void shouldConvertEntityToListDTOClass() throws Exception {
		Category entity = new Category(id: 1, name: 'Kitchen')
		CategoryDTO dto = CategoryDTO.mapFromEntity(entity)
		
		assertEquals(entity.id, dto.id)
		assertEquals(entity.name, dto.name)
	}
	
    @Test
    public void shouldConvertEntityToDTOClass() throws Exception {
        Category entity1 = new Category(id: 1, name: 'Kitchen')
		Category entity2 = new Category(id: 1, name: 'washroom')
        List<Category> categories = Arrays.asList(entity1, entity2);
		List<CategoryDTO> dto = CategoryDTO.mapFromList(categories)
        
        assertEquals(entity1.id, dto[0].id)
        assertEquals(entity1.name, dto[0].name)
		assertEquals(entity2.id, dto[1].id)
		assertEquals(entity2.name, dto[1].name)
    }
	
	

}
