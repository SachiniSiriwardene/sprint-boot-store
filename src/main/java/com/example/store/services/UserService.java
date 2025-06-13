package com.example.store.services;

import com.example.store.entities.Address;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.example.store.repositories.AddressRepository;
import com.example.store.repositories.ProfileRepository;
import com.example.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
