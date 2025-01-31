package dev.coworking.mapper;

import dev.coworking.dto.Attachment;
import dev.coworking.entity.AttachmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AttachmentMapper {

    String toString(AttachmentEntity attachmentEntity);

    @Mapping(target = "id", expression = "java(null)")
    AttachmentEntity toEntity(Attachment photo);

    List<String> listToString(List<AttachmentEntity> attachmentEntities);

    List<AttachmentEntity> listToAttachment(List<Attachment> strings);
}
