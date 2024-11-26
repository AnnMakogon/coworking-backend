package dev.coworking.service;

import dev.coworking.dto.Workspace;
import dev.coworking.mapper.WorkspaceMapper;
import dev.coworking.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    private final WorkspaceMapper workspaceMapper;

    // получение рабочих пространств по id manager
    @Transactional
    public List<Workspace> getWorkspaces(Long managerId) {
        return workspaceRepository.getListByManagerId(managerId)
                .stream().
                map(workspaceMapper::workspaceEntityToWorkspace).
                collect(Collectors.toList());
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

    //апдейт и добавление рп у manager
    @Transactional
    public void updateOrAddWorkspace(Workspace newWorkspace) {
        workspaceRepository.save(
                workspaceMapper.workspaceToWorkspaceEntity(newWorkspace));
    }

    @Transactional
    public void deleteWorkspace(Long id) {
        workspaceRepository.deleteById(id);
    }

}
