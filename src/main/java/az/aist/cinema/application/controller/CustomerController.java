package az.aist.cinema.application.controller;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.customer.CustomerRequestDto;
import az.aist.cinema.application.dto.customer.CustomerResponseDto;
import az.aist.cinema.application.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto dto){
        CustomerResponseDto customer = customerService.registerCustomer(dto);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponseDto>> searchCustomer(@RequestBody List<SearchCriteria> searchCriteria){
        return ResponseEntity.ok(customerService.searchCustomers(searchCriteria));
    }
}


