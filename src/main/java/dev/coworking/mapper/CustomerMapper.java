package dev.coworking.mapper;

import dev.coworking.dto.Customer;
import dev.coworking.entity.CustomerEntity;
import org.mapstruct.*;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {BookingMapper.class})
public interface CustomerMapper {

    Customer customerEntityToCustomer(CustomerEntity customer);

    CustomerEntity customerToCustomerEntity(Customer customer);

}
