package dev.coworking.service;

import dev.coworking.dto.Workspace;
import dev.coworking.dto.WorkspaceCreate;
import dev.coworking.entity.ManagerEntity;
import dev.coworking.entity.TableEntity;
import dev.coworking.entity.WorkspaceEntity;
import dev.coworking.mapper.TableMapper;
import dev.coworking.mapper.WorkspaceMapper;
import dev.coworking.repository.ManagerRepository;
import dev.coworking.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkspaceService {

    @Autowired
    private final WorkspaceRepository workspaceRepository;

    @Autowired
    private final WorkspaceMapper workspaceMapper;

    @Autowired
    private final TableMapper tableMapper;

    @Autowired
    private final ManagerRepository managerRepository;

    // получение рабочих пространств по id manager
    @Transactional
    public Page<Workspace> getWorkspaces(Long managerId) {
        return mapDtoForPage(workspaceRepository.getListByManagerId(managerId, null));
    }

    private Page<Workspace> mapDtoForPage(Page<WorkspaceEntity> workspaces) {
        List<Workspace> workspacedtos = workspaceMapper.workspaceEntityListToWorkspaceList(workspaces.getContent());
        return new PageImpl<>(workspacedtos, workspaces.getPageable(), workspaces.getTotalElements());
    }

    // получение 1 конкретного рп для просмотра и дальнейшей брони
    @Transactional
    public Workspace getConcreteWorkspace(Long id) {
        return workspaceMapper.workspaceEntityToWorkspace(
                workspaceRepository.findById(id).get());
    }

    // получение всех для карты
    @Transactional
    public List<Workspace> getAllWorkspace() {
        return workspaceRepository.findAll()
                .stream().
                map(workspaceMapper::workspaceEntityToWorkspace).
                collect(Collectors.toList());
    }

    //апдейт рп у manager
    @Transactional
    public void updateWorkspace(Workspace workspace) {
        WorkspaceEntity workspaceEntity = workspaceMapper.workspaceToWorkspaceEntity(workspace);
        if (workspaceEntity.getLongitude() != null && workspaceEntity.getLatitude() != null && !Objects.equals(workspaceEntity.getAddress(), "")) {
            workspaceRepository.updateWorkspace(workspaceEntity.getAddress(), workspaceEntity.getLatitude(), workspaceEntity.getLongitude(), workspaceEntity.getName(), workspaceEntity.getId());
        }
    }

    // добавление рп у manager
    @Transactional
    public void addWorkspace(WorkspaceCreate newWorkspace) {
        ManagerEntity manager = managerRepository.getManagerByCredential(newWorkspace.getCredentialId());
        Workspace workspace = workspaceMapper.workspaceCreateToWorkspace(newWorkspace);
        List<TableEntity> tables = tableMapper.tableListToTableEntityList(workspace.getTables());
        WorkspaceEntity workspaceEntity = workspaceMapper.workspaceToWorkspaceEntity(workspace);

        workspaceEntity.setTables(tables);
        workspaceEntity.setManager(manager);
        workspaceRepository.save(workspaceEntity);
    }

    @Transactional
    public void deleteWorkspace(Long id) {
        workspaceRepository.myDeleteById(id);
    }

}
