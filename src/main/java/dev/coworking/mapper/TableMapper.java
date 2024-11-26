package dev.coworking.mapper;

import dev.coworking.dto.Table;
import dev.coworking.entity.AttachmentEntity;
import dev.coworking.entity.TableEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TableMapper extends AttachmentMapper{

    Table tableEntityToTable (TableEntity table);

    TableEntity tableToTableEntity (Table table);
    String toString(AttachmentEntity attachmentEntity);

    List<String> listToString(List<AttachmentEntity> attachmentEntities);
    List<AttachmentEntity> listToAttachment(List<String> strings);
}
