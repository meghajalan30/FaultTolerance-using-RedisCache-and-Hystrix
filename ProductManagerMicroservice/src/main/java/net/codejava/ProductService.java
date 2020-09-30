package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository repo;

	public ProductRepository getRepo() {
		return repo;
	}

	public void setRepo(ProductRepository repo) {
		this.repo = repo;
	}

	public Product get(long id) {
		return repo.findById(id).get();
	}

}
