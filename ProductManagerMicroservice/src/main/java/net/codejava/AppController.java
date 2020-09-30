package net.codejava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class AppController {

	@Autowired
	private ProductService service;


	@RequestMapping("/edit/{id}")
	public Product showEditProductPage(@PathVariable(name = "id") int id) {
		Product product = service.get(id);
		return product;
	}

}
