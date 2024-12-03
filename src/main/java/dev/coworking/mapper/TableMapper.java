package dev.coworking.mapper;

import dev.coworking.dto.Table;
import dev.coworking.entity.AttachmentEntity;
import dev.coworking.entity.TableEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {BookingMapper.class})
public interface TableMapper {

    Table tableEntityToTable (TableEntity table, @Context WorkspaceMapper workspaceMapper);

    TableEntity tableToTableEntity (Table table, @Context WorkspaceMapper workspaceMapper);

}
