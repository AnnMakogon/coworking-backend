package dev.coworking.mapper;

import dev.coworking.dto.Customer;
import dev.coworking.entity.AttachmentEntity;
import dev.coworking.entity.CustomerEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {BookingMapper.class })
public interface CustomerMapper {

    Customer customerEntityToCustomer(CustomerEntity customer);

    CustomerEntity customerToCustomerEntity(Customer customer);

}
