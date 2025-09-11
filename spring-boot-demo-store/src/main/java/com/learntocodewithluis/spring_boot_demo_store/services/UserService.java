package com.learntocodewithluis.spring_boot_demo_store.services;

import com.learntocodewithluis.spring_boot_demo_store.entities.*;
import com.learntocodewithluis.spring_boot_demo_store.repositories.*;
import com.learntocodewithluis.spring_boot_demo_store.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Transactional
    public void fetchProductsProcedure(){
        var products = productRepository.findProductsByPrice(BigDecimal.valueOf(1), BigDecimal.valueOf(54));
        products.forEach(System.out::println);
    }

    @Transactional
    public void addUserAndProfile(){
        var profile = Profile.builder()
                .bio("profile2")
                .phoneNumber("12345")
                .loyaltyPoints(20)
                .dateOfBirth(LocalDate.of(1982, 1, 1))
                .build();
        var user =  User.builder()
                .name("Name prof3")
                .email("email prof3")
                .password("pass prof3")
                .build();
        user.addProfile(profile);
        userRepository.save(user);
    }

    @Transactional
    public void fetchProfilesGreaterThan(){
        var profiles = profileRepository.findProfilesOrderByEmail(2);
        profiles.forEach(p -> {
            System.out.println(p.getId());
            System.out.println(p.getUser().getEmail());
        });
    }

    @Transactional
    public void fetchProfilesGreaterThanFromUser(){
        var users = userRepository.findProfilesOrderByEmail(2);
        users.forEach(u -> {
            System.out.println(u.getId());
            System.out.println(u.getEmail());
        });
    }

    @Transactional
    public void fetchProductsByExample(){
        var product = new Product();

        product.setName("Product");
        var matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIncludeNullValues()
                .withIgnorePaths("id", "description");
        var example = Example.of(product, matcher);
        productRepository.findAll(example).forEach(System.out::println);
    }

    @Transactional
    public void fetchProductsByCriteriaAPI(){
        var products = productRepository.findProductsByCriteria(null, BigDecimal.valueOf(1), BigDecimal.valueOf(10));
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecificationAPI(String name, BigDecimal minPrice, BigDecimal maxPrice){

        Specification<Product> spec = Specification.where(null);
        if  (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }
        productRepository.findAll(spec).forEach(System.out::println);

    }

    public void fetchSortedProducts(){
        var sort = Sort.by("name").and
                (Sort.by("price").descending()
                );
        productRepository.findAll(sort);
    }

    public void fetchPaginatedProducts(int pageNumber, int size){

        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        Page<Product> page = productRepository.findAll(pageRequest);

        var products = page.getContent();
        products.forEach(System.out::println);

        var totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();

        System.out.println("Total elements: " + totalElements);
        System.out.println("Total pages: " + totalPages);

    }

    @Transactional
    public void fetchProductsByCategoryCriteriaAPI() {
        var products = productRepository.findProductsByCategory((byte) 1);
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchProductsByCategorySpecificationAPI(Byte category_id) {
        Specification<Product> spec = Specification.where(null);

        if  (category_id != null) {
            spec = spec.and(ProductSpec.filterByCategory(category_id));
        }
        var products = productRepository.findAll(spec);
        products.forEach(System.out::println);
    }
}
