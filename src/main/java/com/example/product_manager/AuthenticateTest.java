package com.example.product_manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateTest {
    private static final Logger log = LoggerFactory.getLogger(AuthenticateTest.class);

    @GetMapping("/api/open")
    public String open() {
        return "This endpoint is open";
    }

    @GetMapping("/api/authenticated")
    public String authenticated() {
        return "This endpoint required authentication";
    }
}
