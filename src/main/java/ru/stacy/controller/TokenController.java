package ru.stacy.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stacy.service.CustomerService;

@RestController
public class TokenController {
    private final CustomerService customerService;

    @Autowired
    public TokenController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/token")
    public String getToken(@RequestParam("username") final String username, @RequestParam("password") final String password){
        String token = customerService.login(username,password);

        if(StringUtils.isEmpty(token)){
            return "Token not found";
        }

        return token;
    }
}
