package dev.coworking.mapper;

import dev.coworking.dto.Booking;
import dev.coworking.entity.BookingEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target = "table", ignore = true)
    BookingEntity bookingToBookingEntity(Booking booking, @Context TableMapper tableMapper);

    @Mapping(target = "table", ignore = true)
    Booking bookingEntityToBooking(BookingEntity booking, @Context TableMapper tableMapper);

    List<Booking> bookingEntityListToBookingList(List<BookingEntity> bookingEntities);

}
