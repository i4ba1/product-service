package com.product.productservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/productList")
	public Flux<Product> getAll(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/productDetail/{productId}")
	public Mono<ResponseEntity<Product>> getProductById(@PathVariable int productId){
		return productService.getProductById(productId)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/createNewProduct")
	public Mono<Product> createNewProduct(@RequestBody Mono<Product> newProduct){
		return newProduct.flatMap(productService::createProduct);
	}
	
	@PutMapping("/updateProduct/{productId}")
	public Mono<Product> updateProduct(@PathVariable int productId, @RequestBody Mono<Product> existingProduct){
		return productService.updateProduct(productId, existingProduct);
	}
	
	@DeleteMapping("/deleteProduct/{productId}")
	public Mono<Void> deleteProduct(@PathVariable int productId){
		return productService.deleteproduct(productId);
	}
}
