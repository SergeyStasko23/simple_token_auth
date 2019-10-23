package ru.stacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.stacy.domain.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);

//    @Query(value = "SELECT u FROM Customer u WHERE u.username = ?1 AND u.password = ?2")
//    Optional<Customer> login(String username, String password);

    Optional<Customer> findByUsernameAndPassword(String username, String password);
    Optional<Customer> findByToken(String token);
}
