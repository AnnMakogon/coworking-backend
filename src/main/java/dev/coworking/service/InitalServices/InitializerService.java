package dev.coworking.service.InitalServices;

import dev.coworking.entity.*;
import dev.coworking.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class InitializerService {

    private final ManagerRepository managerRepository;

    @Transactional
    public void init() {
        CredentialEntity credentialManager = new CredentialEntity(
                null,
                "myemail@ru",
                new PasswordEntity("1111"),
                Role.MANAGER
        );

        ManagerEntity manager1 = new ManagerEntity(
                null,
                credentialManager,
                "Manager 0",
                new ArrayList<WorkspaceEntity>()
        );
        {
            WorkspaceEntity workspaceEntity0 = new WorkspaceEntity(
                    null,
                    "BC CHG",
                    "Description for BC CHG",
                    "0 0 * * *",
                    51.667194,
                    39.191906,
                    "Koltsovskaya street, 35A, Voronezh",
                    new ArrayList<AttachmentEntity>(),
                    new ArrayList<TableEntity>(),
                    manager1);

            TableEntity tableEntity0 = new TableEntity(
                    null,
                    1,
                    "Descroption for Table 1",
                    workspaceEntity0,
                    new ArrayList<BookingEntity>(),
                    BigDecimal.valueOf(100)
            );
            BookingEntity bookingEntity00 = new BookingEntity(
                    null,
                    LocalDate.now(),
                    tableEntity0,
                    null
            );
            CustomerEntity customerEntity00 = new CustomerEntity(
                    null,
                    null,
                    "Credential0",
                    new ArrayList<>()
            );
            CredentialEntity credential00 = new CredentialEntity(
                    null,
                    "myemail",
                    new PasswordEntity("1111"),
                    Role.CUSTOMER
            );

            BookingEntity bookingEntity01 = new BookingEntity(
                    null,
                    LocalDate.now(),
                    tableEntity0,
                    null
            );
            CustomerEntity customerEntity01 = new CustomerEntity(
                    null,
                    null,
                    "Credential0",
                    new ArrayList<>()
            );
            CredentialEntity credential01 = new CredentialEntity(
                    null,
                    "myemail",
                    new PasswordEntity("1111"),
                    Role.CUSTOMER
            );

            customerEntity00.setCredential(credential00);
            customerEntity01.setCredential(credential01);
            bookingEntity01.setCustomer(customerEntity01);
            bookingEntity00.setCustomer(customerEntity00);
            tableEntity0.getBookings().add(bookingEntity01);
            tableEntity0.getBookings().add(bookingEntity00);
            workspaceEntity0.getTables().add(tableEntity0);
//-------------------------------------------------------------------------------//

            TableEntity tableEntity1 = new TableEntity(
                    null,
                    2,
                    "Descroption for Table 2",
                    workspaceEntity0,
                    new ArrayList<BookingEntity>(),
                    BigDecimal.valueOf(100)
            );
            BookingEntity bookingEntity10 = new BookingEntity(
                    null,
                    LocalDate.now(),
                    tableEntity1,
                    null
            );
            CustomerEntity customerEntity10 = new CustomerEntity(
                    null,
                    null,
                    "Credential0",
                    new ArrayList<>()
            );
            CredentialEntity credential10 = new CredentialEntity(
                    null,
                    "myemail",
                    new PasswordEntity("1111"),
                    Role.CUSTOMER
            );

            BookingEntity bookingEntity11 = new BookingEntity(
                    null,
                    LocalDate.now(),
                    tableEntity1,
                    null
            );
            CustomerEntity customerEntity11 = new CustomerEntity(
                    null,
                    null,
                    "Credential0",
                    new ArrayList<>()
            );
            CredentialEntity credential11 = new CredentialEntity(
                    null,
                    "myemail",
                    new PasswordEntity("1111"),
                    Role.CUSTOMER
            );
            customerEntity11.setCredential(credential11);
            customerEntity10.setCredential(credential10);
            bookingEntity11.setCustomer(customerEntity11);
            bookingEntity10.setCustomer(customerEntity10);
            tableEntity1.getBookings().add(bookingEntity11);
            tableEntity1.getBookings().add(bookingEntity10);
            workspaceEntity0.getTables().add(tableEntity1);

            //-------------------------------------------------------------//

            WorkspaceEntity workspaceEntity02 = new WorkspaceEntity(
                    null,
                    "BC STOLICA",
                    "Description for BC STOLICA",
                    "0 0 * * *",
                    51.693513,
                    39.182276,
                    "Moskovsky prospect, 19B, Voronezh",
                    new ArrayList<AttachmentEntity>(),
                    new ArrayList<TableEntity>(),
                    manager1);

            TableEntity tableEntity02 = new TableEntity(
                    null,
                    1,
                    "Descroption for Table 1",
                    workspaceEntity02,
                    new ArrayList<BookingEntity>(),
                    BigDecimal.valueOf(100)
            );
            BookingEntity bookingEntity002 = new BookingEntity(
                    null,
                    LocalDate.now(),
                    tableEntity02,
                    null
            );
            CustomerEntity customerEntity002 = new CustomerEntity(
                    null, null,
                    "Credential0",
                    new ArrayList<>()
            );
            CredentialEntity credential002 = new CredentialEntity(
                    null,
                    "myemail",
                    new PasswordEntity("1111"),
                    Role.CUSTOMER
            );

            BookingEntity bookingEntity012 = new BookingEntity(
                    null,
                    LocalDate.now(),
                    tableEntity02,
                    null
            );
            CustomerEntity customerEntity012 = new CustomerEntity(
                    null,
                    null,
                    "Credential0",
                    new ArrayList<>()
            );
            CredentialEntity credential012 = new CredentialEntity(
                    null,
                    "myemail",
                    new PasswordEntity("1111"),
                    Role.CUSTOMER
            );
            customerEntity012.setCredential(credential012);
            customerEntity002.setCredential(credential002);
            bookingEntity012.setCustomer(customerEntity012);
            bookingEntity002.setCustomer(customerEntity002);
            tableEntity02.getBookings().add(bookingEntity012);
            tableEntity02.getBookings().add(bookingEntity002);
            workspaceEntity02.getTables().add(tableEntity02);
//-------------------------------------------------------------------------------//


            TableEntity tableEntity12 = new TableEntity(
                    null,
                    2,
                    "Descroption for Table 2",
                    workspaceEntity02,
                    new ArrayList<BookingEntity>(),
                    BigDecimal.valueOf(100)
            );
            BookingEntity bookingEntity102 = new BookingEntity(
                    null,
                    LocalDate.now(),
                    tableEntity12,
                    null
            );
            CustomerEntity customerEntity102 = new CustomerEntity(
                    null,
                    null,
                    "Credential0",
                    new ArrayList<>()
            );
            CredentialEntity credential102 = new CredentialEntity(
                    null,
                    "myemail",
                    new PasswordEntity("1111"),
                    Role.CUSTOMER
            );

            BookingEntity bookingEntity112 = new BookingEntity(
                    null,
                    LocalDate.now(),
                    tableEntity12,
                    null
            );
            CustomerEntity customerEntity112 = new CustomerEntity(
                    null,
                    null,
                    "Credential0",
                    new ArrayList<>()
            );
            CredentialEntity credential112 = new CredentialEntity(
                    null,
                    "myemail",
                    new PasswordEntity("1111"),
                    Role.CUSTOMER
            );
            customerEntity102.setCredential(credential102);
            customerEntity112.setCredential(credential112);
            bookingEntity112.setCustomer(customerEntity112);
            bookingEntity102.setCustomer(customerEntity102);
            tableEntity12.getBookings().add(bookingEntity112);
            tableEntity12.getBookings().add(bookingEntity102);
            workspaceEntity02.getTables().add(tableEntity12);

            manager1.getWorkspaces().add(workspaceEntity0);
            manager1.getWorkspaces().add(workspaceEntity02);
        }

        managerRepository.save(manager1);
    }
}
