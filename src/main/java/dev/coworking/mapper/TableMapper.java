package dev.coworking.mapper;

import dev.coworking.dto.Table;
import dev.coworking.entity.TableEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TableMapper {

    @Mapping(target = "bookings", ignore = true)
    Table tableEntityToTable(TableEntity table);

    @Mapping(target = "bookings", ignore = true)
    TableEntity tableToTableEntity(Table table);

    List<Table> tableEntityListToTableList(List<TableEntity> tableEntities);

    List<TableEntity> tableListToTableEntityList(List<Table> tables);
}
