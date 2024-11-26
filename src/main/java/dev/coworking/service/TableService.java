package dev.coworking.service;

import dev.coworking.dto.Table;
import dev.coworking.mapper.TableMapper;
import dev.coworking.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    //получение столов по id рабочего пространства
    @Transactional
    public List<Table> getTable(Long workspaceId){
        return tableRepository.getListByWorkspace(workspaceId)
                .stream().map(tableMapper::tableEntityToTable).collect(Collectors.toList());
    }

    @Transactional
    public void updateTable(Table newTable){
        tableRepository.save(
                tableMapper.tableToTableEntity(newTable));
    }

    @Transactional
    public void deleteTable(Long id){
        tableRepository.deleteById(id);
    }

}
