package com.example.store.services;

import com.example.store.entities.Address;
import com.example.store.entities.Category;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.example.store.repositories.AddressRepository;
import com.example.store.repositories.ProfileRepository;
import com.example.store.repositories.UserRepository;
import com.example.store.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void showEntityStates(){
        var user = User.builder()
                .name("John")
                .email("John@gmail.com")
                .password("John123")
                .build();
        if (entityManager.contains(user)) {
            System.out.println("User is in the persistence context");
        } else {
            System.out.println("User is transient/ detached context");
        }
        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("User is in the persistence context");
        } else {
            System.out.println("User is transient/ detached context");
        }
    }

    //to keep the transaction open to retrieve the user
    @Transactional
    public void showRelatedEntities() {

        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddresses() {
        var address = addressRepository.findById(1L).orElseThrow();
        //System.out.println(user.getAddresses());
    }

    public void persistRelatedEntities() {

        var user = User.builder()
                .name("John")
                .email("john@abc.com")
                .password("john123")
                .build();
        var address = Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();

        user.addAddress(address);
        userRepository.save(user);
        //addressRepository.save(address);
    }

    @Transactional
    public void deleteRelated() {
        userRepository.deleteById(20L);
        var user = userRepository.findById(21L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }


    @Transactional
    public void addToWishList() {
        var user = userRepository.findById(22L).orElseThrow();
        var products = productRepository.findAll();
        products.forEach(user::addFavoriteProduct);
        userRepository.save(user);
    }

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10), (byte) 1);
    }

    public void fetchProducts() {
        var products = productRepository.findByCategory(new Category((byte)1));
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchProduct() {
        var products = productRepository.findProduct(BigDecimal.valueOf(1), BigDecimal.valueOf(1200));
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchSampleProducts() {
       var product = new Product();
       product.setName("product");
       var matcher = ExampleMatcher.matching()
               .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
       var example = Example.of(product, matcher);
       var products = productRepository.findAll(example);
       products.forEach(System.out::println);
    }

    @Transactional
    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria(null, BigDecimal.valueOf(1),
                BigDecimal.valueOf(10), null);
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        var products = productRepository.findAll(spec);
        products.forEach(System.out::println);
    }

    public void fetchSortedProducts() {
       var sort =  Sort.by("name").and(
                Sort.by("price").descending()
        );
        productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> pages = productRepository.findAll(pageRequest);
        var products  = pages.getContent();
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithTags();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void findProfiles() {
        var profiles = profileRepository.findUserProfiles(2);
        profiles.forEach(profile ->
                System.out.println(profile.getId() + ": "+ profile.getEmail())

        );
    }
}
