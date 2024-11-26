package dev.coworking.service;

import dev.coworking.dto.Manager;
import dev.coworking.mapper.ManagerMapper;
import dev.coworking.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    @Transactional
    public Manager getPersInfo(Long id){
        return managerMapper.managerEntityToManager(
                managerRepository.findById(id).get());
    }

    @Transactional
    public Manager updateManager(Manager changingManager){
        managerRepository.save(
                managerMapper.managerToManagerEntity(changingManager));
        return changingManager;
    }
}
