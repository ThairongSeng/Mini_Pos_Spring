package co.thairong.mini_pos.mapper;

import co.thairong.mini_pos.dto.CustomerDto;
import co.thairong.mini_pos.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToCustomerDto(Customer customer);

}
