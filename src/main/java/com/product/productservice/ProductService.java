package com.product.productservice;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService implements InterfaceProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Flux<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Mono<Product> getProductById(int productId) {
		return productRepository.findById(productId);
	}

	@Override
	public Mono<Product> createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Mono<Product> updateProduct(final Product product) {
		return productRepository.findById(product.getId()).flatMap(p -> {
			p.setDescription(product.getDescription());
			p.setPrice(product.getPrice());
			return productRepository.save(p);
		}).switchIfEmpty(productRepository.save(product.setAsNew()));// save if the product with id is not present
	}

	@Override
	public Mono<Void> deleteproduct(int productId) {
		return productRepository.deleteById(productId);
	}

}
