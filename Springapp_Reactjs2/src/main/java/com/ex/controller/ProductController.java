package com.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.entity.Product;
import com.ex.services.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/save")
	public String saveEmp(@RequestBody Product product) {
		productService.createBook(product);
		return "Procut addedd successfully";
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	@GetMapping
	public List<Product> getAllProduct(){
		return productService.getAllProduct();
	}
	
	@PutMapping("/edit/{id}")
	public String updateProduct(@PathVariable Long id, @RequestBody Product product) {
		productService.updateProductItem(id, product);
		return "Product updated successfully";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteProd(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "Product deleted successfully";
	}
	
	
}
