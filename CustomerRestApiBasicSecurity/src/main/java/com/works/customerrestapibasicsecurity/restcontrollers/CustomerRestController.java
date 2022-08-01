package com.works.customerrestapibasicsecurity.restcontrollers;

import com.works.customerrestapibasicsecurity.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customer")
@RestController
public class CustomerRestController {

    final CustomerService cService;

    public CustomerRestController(CustomerService cService) {
        this.cService = cService;
    }


    @GetMapping("/single")
    public ResponseEntity single(@RequestParam String id ) {
        return cService.single(id);
    }
}
