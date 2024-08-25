package co.thairong.mini_pos.controller;


import co.thairong.mini_pos.base.BaseRest;
import co.thairong.mini_pos.dto.CompanyDto;
import co.thairong.mini_pos.dto.CustomerDto;
import co.thairong.mini_pos.dto.PageDto;
import co.thairong.mini_pos.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public BaseRest<?> createCustomer(@Valid @RequestBody CustomerDto customerDto) {

        CustomerDto customerDto1 = customerService.createCustomer(customerDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Customer has been created successfully")
                .data(customerDto1)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("{id}")
    public BaseRest<?> getCustomerById(@PathVariable Long id) {

        CustomerDto customerDto = customerService.getByCustomerId(id);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Customer has been found successfully")
                .data(customerDto)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }


    @PutMapping("{id}")
    public BaseRest<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto) {

        CustomerDto customerDto1 = customerService.updateCustomer(id, customerDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Customer has been updated successfully")
                .data(customerDto1)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @GetMapping("/page")
    public BaseRest<?> getCustomers(@RequestParam(required = false) Map<String, String> params) {

        Page<CustomerDto> customerDtoPage = customerService.getCustomers(params);
        PageDto pageDto = new PageDto(customerDtoPage);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Customers have been updated successfully")
                .data(pageDto)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
