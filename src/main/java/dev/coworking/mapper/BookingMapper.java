package dev.coworking.mapper;

import dev.coworking.dto.Booking;
import dev.coworking.entity.AttachmentEntity;
import dev.coworking.entity.BookingEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper extends AttachmentMapper{

    BookingEntity bookingToBookingEntity(Booking booking);
    Booking bookingEntityToBooking(BookingEntity booking);

    String toString(AttachmentEntity attachmentEntity);

    @Mapping(target = "photo", source = "photo")
    AttachmentEntity toEntity(String photo);

    List<String> listToString(List<AttachmentEntity> attachmentEntities);
    List<AttachmentEntity> listToAttachment(List<String> strings);
}
