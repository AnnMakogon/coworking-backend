package dev.coworking.service.InitalServices;

import dev.coworking.entity.*;
import dev.coworking.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class InitialTestManager {

    private final ManagerRepository managerRepository;

    @Transactional
    public void init() {
        CredentialEntity credentialManager = new CredentialEntity(
                null,
                "myTestEmail@ru",
                new PasswordEntity("1111"),
                Role.MANAGER
        );

        ManagerEntity manager1 = new ManagerEntity(
                null,
                credentialManager,
                "Manager Test",
                new ArrayList<WorkspaceEntity>()
        );

        WorkspaceEntity workspaceEntity0 = new WorkspaceEntity(
                null,
                "BC Moskovskij",
                "Description for Moskovskij",
                "0 0 * * *",
                51.678609,
                39.185682,
                "Moskovskij avenue, 4, Voronezh",
                new ArrayList<AttachmentEntity>(),
                new ArrayList<TableEntity>(),
                manager1);

        WorkspaceEntity workspaceEntity1 = new WorkspaceEntity(
                null,
                "BC Prem'er",
                "Description for Prem'er",
                "0 0 * * *",
                51.671430,
                39.203336,
                "Komissarzhevskoj street, 10, Voronezh",
                new ArrayList<AttachmentEntity>(),
                new ArrayList<TableEntity>(),
                manager1);

        /*TableEntity tableEntity0 = new TableEntity(
                null,
                1,
                "Description for Table 0",
                workspaceEntity0,
                new ArrayList<BookingEntity>()
        );
        TableEntity tableEntity1 = new TableEntity(
                null,
                2,
                "Description for Table 1",
                workspaceEntity0,
                new ArrayList<BookingEntity>()
        );
        TableEntity tableEntity2 = new TableEntity(
                null,
                1,
                "Description for Table 2",
                workspaceEntity0,
                new ArrayList<BookingEntity>()
        );

        TableEntity tableEntity01 = new TableEntity(
                null,
                1,
                "Description for Table 0",
                workspaceEntity1,
                new ArrayList<BookingEntity>()
        );
        TableEntity tableEntity11 = new TableEntity(
                null,
                2,
                "Description for Table 1",
                workspaceEntity1,
                new ArrayList<BookingEntity>()
        );
        TableEntity tableEntity21 = new TableEntity(
                null,
                1,
                "Description for Table 2",
                workspaceEntity1,
                new ArrayList<BookingEntity>()
        );

        workspaceEntity0.getTables().add(tableEntity0);
        workspaceEntity0.getTables().add(tableEntity1);
        workspaceEntity0.getTables().add(tableEntity2);

        workspaceEntity1.getTables().add(tableEntity01);
        workspaceEntity1.getTables().add(tableEntity11);
        workspaceEntity1.getTables().add(tableEntity21);

        manager1.getWorkspaces().add(workspaceEntity0);
        manager1.getWorkspaces().add(workspaceEntity1);

        managerRepository.save(manager1);*/
    }
}
