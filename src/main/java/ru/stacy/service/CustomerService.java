package ru.stacy.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.stacy.domain.Customer;
import ru.stacy.repository.CustomerRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String login(String username, String password) {
        Optional<Customer> customerOptional = customerRepository.findByUsernameAndPassword(username, password);

        if(customerOptional.isPresent()) {
            String token = UUID.randomUUID().toString();
            Customer customer = customerOptional.get();
            customer.setToken(token);
            customerRepository.save(customer);
            return token;
        }

        return StringUtils.EMPTY;
    }


    public Optional<User> findByToken(String token) {
        Optional<Customer> customerOptional = customerRepository.findByToken(token);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            User user = new User(customer.getUsername(), customer.getPassword(),
                    true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }
}
