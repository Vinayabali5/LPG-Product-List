package uk.ac.reigate.applications.api

import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.is
import static org.mockito.Mockito.when
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc

import uk.ac.reigate.applications.domain.Category
import uk.ac.reigate.applications.service.CategoryService


@WebMvcTest(CategoriesApi.class)
@AutoConfigureRestDocs
class CategoriesApiTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService service;

	// Sample data
	Category one = new Category(id: 1, name: 'Kitchen')
	Category two = new Category(id: 2, name: 'WashRoom')

	@BeforeEach
	public void setupService() {
		when(service.findAll()).thenReturn([one, two])
	}

	@WithMockUser(value = "user@reigate.ac.uk", password = "password")
    @Test
	public void shouldReturnAnArrayOfJson() throws Exception {
		this.mockMvc.perform(get("/categories"))
				.andExpect(status().isOk())
				.andExpect(jsonPath('$', hasSize(2)))
				.andExpect(jsonPath('$[0].id', is(one.id)))
				.andExpect(jsonPath('$[0].name', is(one.name)))
				.andDo(print())
				.andDo(document("categories-api-get-all"));
	}

}
