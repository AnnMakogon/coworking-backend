package dev.coworking.mapper;

import dev.coworking.dto.Workspace;
import dev.coworking.entity.WorkspaceEntity;
import org.mapstruct.*;
import org.springframework.context.annotation.Primary;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {TableMapper.class, AttachmentMapper.class})
public interface WorkspaceMapper {

    @Mapping(target = "tables", ignore = true)
    @Mapping(target = "attachments", ignore = true)
    Workspace workspaceEntityToWorkspace(WorkspaceEntity workspace);

    @Mapping(target = "tables", ignore = true)
    WorkspaceEntity workspaceToWorkspaceEntity(Workspace workspace);

}
