package com.learntocodewithluis.spring_boot_demo_store.services;

import com.learntocodewithluis.spring_boot_demo_store.entities.Address;
import com.learntocodewithluis.spring_boot_demo_store.entities.Category;
import com.learntocodewithluis.spring_boot_demo_store.entities.Product;
import com.learntocodewithluis.spring_boot_demo_store.entities.User;
import com.learntocodewithluis.spring_boot_demo_store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public void showEntityStates(){
        var user = User.builder()
                .name("Luis test")
                .email("ga@gmail.com")
                .password("e34")
                .build();

        if (entityManager.contains(user)){
            System.out.println("Persistent state");
        }else{
            System.out.println("Transient or detached");
        }
        userRepository.save(user);
        if (entityManager.contains(user)){
            System.out.println("Persistent state");
        }else{
            System.out.println("Transient or detached");
        }
    }

    @Transactional
    public void showRelatedEntities(){
        var profile = profileRepository.findById(1L).orElseThrow();
        System.out.println(profile.getUser().getEmail());

    }

    public void fetchAddress(){
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println("Address: " + address);
    }

    public void persistRelated(){

        var user = User.builder()
                .name("Name 2")
                .email("email2")
                .password("password2")
                .build();

        var address = Address.builder()
                .street("street2")
                .city("city2")
                .state("state2")
                .zip("zip2")
                .build();
        user.addAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated(){
        //userRepository.deleteById(1L);
        var user = userRepository.findById(4L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    public void addNewProductNewCategory(){
        var newProduct = Product.builder()
                .name("Product 3")
                .description("Description 3")
                .price(BigDecimal.valueOf(32.4))
                .build();

        var newCategory =  Category.builder()
                .name("Category 3")
                .build();
        newProduct.setCategory(newCategory);
        productRepository.save(newProduct);
    }

    @Transactional
    public void addNewProductForExistingCategory(){
        var newProduct = Product.builder()
                .name("Product 2")
                .description("Description 2")
                .price(BigDecimal.valueOf(31.4))
                .build();

        var existingCategory =  categoryRepository
                .findById((byte) 2).orElseThrow();
        newProduct.setCategory(existingCategory);
        productRepository.save(newProduct);
    }

    @Transactional
    public void addNewProductsToUserWishlist(){
        var user = userRepository.findById(4L).orElseThrow();
        productRepository.findAll().forEach( p -> {
            System.out.println(p);
            user.getProducts().add(p);
        });

    }
    //No need to modify wishlist schema
    @Transactional
    public void deleteExistingProduct(){
        var category = categoryRepository.findById((byte) 2).orElseThrow();
        var product = productRepository.findById(2L).orElseThrow();
        category.getProducts().remove(product);

        userRepository.findAll().forEach( user -> {
            System.out.println(user.getProducts());
            user.getProducts().remove(product);
        });
    }

    @Transactional
    public void deleteExistingProductWithPreviousModification(){
        productRepository.deleteById(1L);
    }

    @Transactional
    public void updatePriceByCategory(){
        productRepository.updatePriceByCategory(BigDecimal.valueOf(21.3), (byte) 2);
    }

    public void fetchProducts(){
        var products = productRepository.findByCategory(new Category((byte)2));
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUser(){
        var user = userRepository.findByEmail("email2").orElseThrow();
        System.out.println(user);

    }

    @Transactional
    public void fetchUsers(){
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }
}
