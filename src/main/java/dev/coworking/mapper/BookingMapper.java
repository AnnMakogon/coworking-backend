package dev.coworking.mapper;

import dev.coworking.dto.Booking;
import dev.coworking.entity.BookingEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { CustomerMapper.class, TableMapper.class})
public interface BookingMapper {

    BookingEntity bookingToBookingEntity(Booking booking, @Context TableMapper tableMapper);
    Booking bookingEntityToBooking(BookingEntity booking, @Context TableMapper tableMapper);

}
