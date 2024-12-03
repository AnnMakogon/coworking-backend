package dev.coworking.controller;

import dev.coworking.dto.Table;
import dev.coworking.service.TableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class TableController {

    private final TableService tableService;

    //получение столов по id рабочего пространства
    @GetMapping(value = "table/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Table> getTable(@PathVariable("id") Long id){
        return tableService.getTable(id);
    }

    @PutMapping(value = "table", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTable(@RequestBody Table newTable){
        tableService.updateTable(newTable);
    }

    @DeleteMapping(value = "table/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTable(@PathVariable("id")Long id){
        tableService.deleteTable(id);
    }
}
