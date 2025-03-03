import dev.coworking.CoworkingApplication;
import dev.coworking.dto.Booking;
import dev.coworking.entity.BookingEntity;
import dev.coworking.mapper.BookingMapper;
import dev.coworking.mapper.TableMapper;
import dev.coworking.repository.BookingRepository;
import dev.coworking.repository.CustomerRepository;
import dev.coworking.repository.TableRepository;
import dev.coworking.repository.WorkspaceRepository;
import dev.coworking.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CoworkingApplication.class)
@Testcontainers
@ExtendWith(SpringExtension.class)
public class MyIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("1234");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    //
    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private WorkspaceRepository workspaceRepository;

    @Mock
    private TableMapper tableMapper;

    @Mock
    private CustomerRepository customerRepository;

    @Mock //todo убрать и заменить на реальный
    private TableRepository tableRepository;

    @InjectMocks
    private BookingService bookingService;

    @Autowired
    public void MyServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBookingTable() {
        Long tableId = 21L;
        LocalDate date = LocalDate.of(2025,1,15);
        when(bookingRepository.getListByTable(tableId, date, null)).thenReturn(new PageImpl<>(new ArrayList<>())); // не нужно, убрать при реадльной бд

        Page<Booking> bookings = bookingService.getBookingTable(tableId, date);

        // Проверка
        assertNotNull(bookings);
        assertTrue(bookings.isEmpty());
        verify(bookingRepository, times(1)).getListByTable(tableId, date, null);
    }

    @Test
    public void testGetPersBooking() {
        Long customerId = 46L;
        // Настройка мока
        when(bookingRepository.getListByCustomer(customerId)).thenReturn(List.of(new BookingEntity()));

        // Сам метод
        List<Booking> bookings = bookingService.getPersBooking(customerId);

        // Проверка
        assertNotNull(bookings);
        assertFalse(bookings.isEmpty());
        verify(bookingRepository, times(1)).getListByCustomer(customerId);
    }

    @Test
    public void testUpdateBooking() {
        Booking newBooking = new Booking();
        when(bookingMapper.bookingToBookingEntity(newBooking, tableMapper)).thenReturn(new BookingEntity());

        bookingService.updateBooking(newBooking);

        verify(bookingRepository, times(1)).save(any(BookingEntity.class));
    }

    @Test
    public void testDeleteBooking() {
        Long bookingId = 1L;

        bookingService.deleteBooking(bookingId);

        verify(bookingRepository, times(1)).myDeleteById(bookingId);
    }

}
