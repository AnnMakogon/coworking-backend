package dev.coworking.mapper;

import dev.coworking.dto.Manager;
import dev.coworking.entity.ManagerEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {WorkspaceMapper.class})
public interface ManagerMapper {

    Manager managerEntityToManager(ManagerEntity managerEntity);

    ManagerEntity managerToManagerEntity(Manager manager);

}
