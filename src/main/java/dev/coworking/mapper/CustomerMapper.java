package dev.coworking.mapper;

import dev.coworking.dto.Customer;
import dev.coworking.entity.AttachmentEntity;
import dev.coworking.entity.CustomerEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper extends AttachmentMapper{

    Customer customerEntityToCustomer(CustomerEntity customer);

    CustomerEntity customerToCustomerEntity(Customer customer);
    String toString(AttachmentEntity attachmentEntity);

    @Mapping(target = "photo", source = "photo")
    AttachmentEntity toEntity(String photo);

    List<String> listToString(List<AttachmentEntity> attachmentEntities);
    List<AttachmentEntity> listToAttachment(List<String> strings);

}
