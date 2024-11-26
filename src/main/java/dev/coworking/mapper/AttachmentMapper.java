package dev.coworking.mapper;

import dev.coworking.entity.AttachmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AttachmentMapper {

    String toString(AttachmentEntity attachmentEntity);

    @Mapping(target = "photo", source = "photo")
    @Mapping(target = "id", expression = "java(null)")
    AttachmentEntity toEntity(String photo);

    List<String> listToString(List<AttachmentEntity> attachmentEntities);
    List<AttachmentEntity> listToAttachment(List<String> strings);
}
