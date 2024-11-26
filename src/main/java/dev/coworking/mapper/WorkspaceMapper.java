package dev.coworking.mapper;

import dev.coworking.dto.Workspace;
import dev.coworking.entity.WorkspaceEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WorkspaceMapper extends AttachmentMapper {

    @Mapping(target = "tables", source = "tables")
    Workspace workspaceEntityToWorkspace(WorkspaceEntity workspace);

    @Mapping(target = "tables", source = "tables")
    WorkspaceEntity workspaceToWorkspaceEntity(Workspace workspace);

}
