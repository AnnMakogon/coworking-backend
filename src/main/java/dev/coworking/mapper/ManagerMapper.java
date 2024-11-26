package dev.coworking.mapper;

import dev.coworking.dto.Manager;
import dev.coworking.entity.AttachmentEntity;
import dev.coworking.entity.ManagerEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ManagerMapper extends AttachmentMapper{

    Manager managerEntityToManager (ManagerEntity managerEntity);

    ManagerEntity managerToManagerEntity(Manager manager);
    String toString(AttachmentEntity attachmentEntity);

    List<String> listToString(List<AttachmentEntity> attachmentEntities);
    List<AttachmentEntity> listToAttachment(List<String> strings);

}
