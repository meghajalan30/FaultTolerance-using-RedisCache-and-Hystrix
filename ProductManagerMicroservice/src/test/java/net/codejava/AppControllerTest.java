package net.codejava;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration

class AppControllerTest {

	@Autowired
	private MockMvc mockMvc;


	@Test
	void testShowEditProductPage() throws Exception
	{
		Product product=new Product();
		product.setId((long) 5);
		product.setBrand("Apple");
		product.setName("IPhone");
		product.setMadein("China");
		product.setPrice((float) 85000.0);

		ProductRepository productRepository1 = mock(ProductRepository.class);
		when(productRepository1.findById(anyLong())).thenReturn(java.util.Optional.of(product));
		ProductService productServiceMock=mock(ProductService.class);
		productServiceMock.setRepo(productRepository1);
		when(productServiceMock.get(anyLong())).thenReturn(product);

		AppController appController=new AppController();
		appController.setService(productServiceMock);
		mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
		mockMvc.perform(MockMvcRequestBuilders.
				get("/product/edit/5")).
				andExpect(status().isOk()).
				//andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
						andExpect(MockMvcResultMatchers.jsonPath("$.name", is("IPhone")));
		//andExpect(MockMvcResultMatchers.jsonPath("$.size()", is(productList.size())));
	}


}
