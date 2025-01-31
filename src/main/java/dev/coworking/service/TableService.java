package dev.coworking.service;

import dev.coworking.dto.Table;
import dev.coworking.entity.TableEntity;
import dev.coworking.mapper.TableMapper;
import dev.coworking.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    //получение столов по id рабочего пространства
    @Transactional
    public Page<Table> getTable(Long workspaceId) {
        return mapDtoForPage(tableRepository.getListByWorkspace(workspaceId, null));
    }

    private Page<Table> mapDtoForPage(Page<TableEntity> tables) {
        List<Table> tabledtos = tableMapper.tableEntityListToTableList(tables.getContent());
        return new PageImpl<>(tabledtos, tables.getPageable(), tables.getTotalElements());
    }

    @Transactional
    public void updateTable(Table newTable) {
        tableRepository.updateTable(newTable.getDescription(), newTable.getPrice(), newTable.getId());
    }

    @Transactional
    public void deleteTable(Long id) {
        tableRepository.deleteBookingsByTableId(id);
        tableRepository.myDeleteById(id);
    }

}
