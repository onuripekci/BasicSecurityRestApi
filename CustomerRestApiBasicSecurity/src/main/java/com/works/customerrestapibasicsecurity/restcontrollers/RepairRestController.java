package com.works.customerrestapibasicsecurity.restcontrollers;

import com.works.customerrestapibasicsecurity.entities.Fault;
import com.works.customerrestapibasicsecurity.entities.User;
import com.works.customerrestapibasicsecurity.services.RepairService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/repair")
@RestController
public class RepairRestController {

    final RepairService rService;

    public RepairRestController(RepairService rService) {
        this.rService = rService;
    }


    @PostMapping("/register")
    private ResponseEntity register(@RequestBody User user) {
        return rService.register(user);
    }

    @PostMapping("/save")
    private ResponseEntity save(@RequestBody Fault fault) {
        return rService.save(fault);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return rService.list();
    }

}
