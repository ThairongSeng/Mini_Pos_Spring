package co.thairong.mini_pos.service;

import co.thairong.mini_pos.model.dto.CustomerDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto getByCustomerId(Long customerId);

    void deleteCustomer(Long customerId);

    CustomerDto updateCustomer(Long customerId, CustomerDto customerDto);

    Page<CustomerDto> getCustomers(Map<String, String> params);

}
