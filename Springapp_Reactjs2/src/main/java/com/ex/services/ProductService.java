package com.ex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ex.entity.Product;
import com.ex.entity.User_Info;
import com.ex.repository.ProductRepository;
import com.ex.repository.UserRepository;

@Service
public class ProductService implements UserService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void createBook(Product product) {
		productRepository.save(product);	
	}
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public Product getProductById(Long id) {
		return productRepository.findById(id).get();
	}
	
	public void updateProductItem(Long id, Product updateProduct) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		
		if(optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			
			product.setProduct_name(updateProduct.getProduct_name());
			product.setDescription(updateProduct.getDescription());
			product.setPrice(updateProduct.getPrice());
			
			productRepository.save(product);
		}
	}
	
	public void deleteProduct(Long Id) {
		productRepository.deleteById(Id);
	}

	@Override
	public User_Info findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@Override
	public User_Info saveUser(User_Info user_Info) {
		user_Info.setRole("Role_USER");
		user_Info.setPassword(passwordEncoder.encode(user_Info.getPassword()));
		return userRepository.save(user_Info);
	}

}
