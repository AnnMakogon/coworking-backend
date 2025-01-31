package dev.coworking.mapper;

import dev.coworking.dto.Workspace;
import dev.coworking.dto.WorkspaceCreate;
import dev.coworking.entity.WorkspaceEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {TableMapper.class, AttachmentMapper.class})
public interface WorkspaceMapper {

    @Mapping(target = "tables", ignore = true)
    @Mapping(target = "attachments", ignore = true)
    Workspace workspaceEntityToWorkspace(WorkspaceEntity workspace);

    @Mapping(target = "tables", ignore = true)
    WorkspaceEntity workspaceToWorkspaceEntity(Workspace workspace);

    List<Workspace> workspaceEntityListToWorkspaceList(List<WorkspaceEntity> workspaceEntity);

    Workspace workspaceCreateToWorkspace(WorkspaceCreate workspaceCreate);

}
