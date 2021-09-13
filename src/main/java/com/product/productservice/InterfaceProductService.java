package com.product.productservice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InterfaceProductService {
	Flux<Product> getAllProducts();
	Mono<Product> getProductById(int productId);
	Mono<Product> createProduct(final Product product);
	Mono<Product> updateProduct(int productid, final Mono<Product> productMono);
	Mono<Void> deleteproduct(int productId);
}
