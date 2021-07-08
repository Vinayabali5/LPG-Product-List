package uk.ac.reigate.applications.dto

import static org.junit.jupiter.api.Assertions.*

import java.text.SimpleDateFormat

import org.junit.jupiter.api.Test

import uk.ac.reigate.applications.domain.Category
import uk.ac.reigate.applications.domain.Product
import uk.ac.reigate.applications.dto.application.ProductDTO

class ProductDTOTests {

    @Test
    public void shouldConvertEntityToDTOClass() throws Exception {
        Product entity = new Product(id: 1, category: new Category(id: 1, name:'kitchen'), name: 'Kitchen Set', description: 'kitchen knife', creationDate : new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'), updateDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'), lastPurchasedDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/10/01'))
        ProductDTO dto = ProductDTO.mapFromEntity(entity)
        
        assertEquals(entity.id, dto.id)
        assertEquals(entity.name, dto.name)
		assertEquals(entity.description, dto.description)
		assertEquals(entity.category.id, dto.categoryId)
		assertEquals(entity.creationDate, dto.creationDate)
		assertEquals(entity.updateDate, dto.updateDate)
		assertEquals(entity.lastPurchasedDate, dto.lastPurchasedDate)
		
    }
	
	@Test
	public void shouldConvertEntityToListDTOClass() throws Exception {
		Product entity1 = new Product(id: 1, category: new Category(id: 1, name:'kitchen'), name: 'Kitchen Set', description: 'kitchen knife', creationDate : new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'), updateDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'), lastPurchasedDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/10/01'))
		Product entity2 = new Product(id: 1, category: new Category(id: 1, name:'Washroom'), name: 'washroom', description: 'soap', creationDate : new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'), updateDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/09/01'), lastPurchasedDate: new SimpleDateFormat("yyyy/MM/dd").parse('2021/10/01'))
		List<Product> products = Arrays.asList(entity1, entity2);
		List<ProductDTO> dto = ProductDTO.mapFromList(products)
		
		assertEquals(entity1.id, dto[0].id)
		assertEquals(entity1.name, dto[0].name)
		assertEquals(entity1.description, dto[0].description)
		assertEquals(entity1.category.id, dto[0].categoryId)
		assertEquals(entity1.creationDate, dto[0].creationDate)
		assertEquals(entity1.updateDate, dto[0].updateDate)
		assertEquals(entity1.lastPurchasedDate, dto[0].lastPurchasedDate)
		
		assertEquals(entity2.id, dto[1].id)
		assertEquals(entity2.name, dto[1].name)
		assertEquals(entity2.description, dto[1].description)
		assertEquals(entity2.category.id, dto[1].categoryId)
		assertEquals(entity2.creationDate, dto[1].creationDate)
		assertEquals(entity2.updateDate, dto[1].updateDate)
		assertEquals(entity2.lastPurchasedDate, dto[1].lastPurchasedDate)
		
	}

}
