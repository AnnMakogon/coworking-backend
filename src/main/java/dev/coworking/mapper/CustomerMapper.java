package dev.coworking.mapper;

import dev.coworking.dto.Customer;
import dev.coworking.entity.AttachmentEntity;
import dev.coworking.entity.CastomerEntity;
import org.mapstruct.*;

import java.util.List;

//todo убрать ignore в reportingpolitcy
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper extends AttachmentMapper{

    Customer castomerEntityToCastomer(CastomerEntity castomer);

    CastomerEntity castomerToCastomerEntity(Customer castomer);
    String toString(AttachmentEntity attachmentEntity);

    @Mapping(target = "photo", source = "photo")
    AttachmentEntity toEntity(String photo);

    List<String> listToString(List<AttachmentEntity> attachmentEntities);
    List<AttachmentEntity> listToAttachment(List<String> strings);

}
