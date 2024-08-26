package co.thairong.mini_pos.service.impl;

import co.thairong.mini_pos.model.dto.CustomerDto;
import co.thairong.mini_pos.model.entity.Customer;
import co.thairong.mini_pos.mapper.CustomerMapper;
import co.thairong.mini_pos.repository.CustomerRepository;
import co.thairong.mini_pos.service.CustomerService;
import co.thairong.mini_pos.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);

        customerRepository.save(customer);

        return customerMapper.customerToCustomerDto(customer);
    }

    @Override
    public CustomerDto getByCustomerId(Long customerId) {

        Customer customer = customerRepository.findCustomerByIdAndIsDeletedFalse(customerId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Customer with id %d not found", customerId)
                ));

        return customerMapper.customerToCustomerDto(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        CustomerDto customerDto = getByCustomerId(customerId);
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);

        customer.setId(customerId);
        customer.setDeleted(true);

        customerRepository.save(customer);
    }


    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {

        CustomerDto customerDto1 = getByCustomerId(customerId);
        Customer customer = customerMapper.customerDtoToCustomer(customerDto1);

        customer.setId(customerId);
        customer.setCustomerLocalName(customerDto.customerLocalName());
        customer.setCustomerEngName(customerDto.customerEngName());
        customer.setCustomerEmail(customerDto.customerEmail());
        customer.setCustomerPhone(customerDto.customerPhone());
        customer.setCustomerAddress(customerDto.customerAddress());

        customerRepository.save(customer);

        return customerMapper.customerToCustomerDto(customer);
    }


    @Override
    public Page<CustomerDto> getCustomers(Map<String, String> params) {
        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        String searchName = PageUtil.SEARCH_NAME;

        // Extract pagination parameters
        if (params.containsKey(PageUtil.PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        if (params.containsKey(PageUtil.PAGE_NUM)) {
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUM));
        }

        // Create Pageable object
        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);

        // Fetch paginated result
        Page<Customer> customerPage;
        if (params.containsKey(searchName)) {
            String customerEngName = params.get(searchName);
            customerPage = customerRepository.findByCustomerEngNameContainingIgnoreCaseAndIsDeletedFalse(customerEngName, pageable);
        } else {
            customerPage = customerRepository.findByIsDeletedFalseOrderByIdDesc(pageable);
        }

        // Convert to DTOs
        Page<CustomerDto> customerDtos = customerPage.map(customerMapper::customerToCustomerDto);

        return customerDtos;
    }
}
