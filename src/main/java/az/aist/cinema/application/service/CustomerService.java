package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.customer.CustomerRequestDto;
import az.aist.cinema.application.dto.customer.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto registerCustomer(CustomerRequestDto request);
    List<CustomerResponseDto> getAllCustomers();
    List<CustomerResponseDto> searchCustomers(List<SearchCriteria> request);

}
