package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.SearchSpecification;
import az.aist.cinema.application.dto.account.AccountRegisterRequestDto;
import az.aist.cinema.application.dto.customer.CustomerRequestDto;
import az.aist.cinema.application.dto.customer.CustomerResponseDto;
import az.aist.cinema.application.entity.CustomerEnt;
import az.aist.cinema.application.mapper.CustomerMapper;
import az.aist.cinema.application.repository.CustomerRepository;
import az.aist.cinema.application.service.AccountService;
import az.aist.cinema.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountService accountService;

    @Override
    public void registerCustomer(CustomerRequestDto request) {
        CustomerEnt customerEnt = customerMapper.toEntity(request);
        AccountRegisterRequestDto account = request.getAccount();
        accountService.createAccount(account);
        customerRepository.save(customerEnt);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerMapper.toDtoList(customerRepository.findAll());
    }

    @Override
    public List<CustomerResponseDto> searchCustomers(List<SearchCriteria> request) {
        List<Specification<CustomerEnt>> specs = new ArrayList<>();
        for (SearchCriteria criteria : request) {
            specs.add(new SearchSpecification<>(criteria));
        }

        Specification<CustomerEnt> spec = specs.stream().reduce(Specification::and).orElse(null);
        List<CustomerEnt> all = customerRepository.findAll(spec);
        return customerMapper.toDtoList(all);
    }
}
