package dev.coworking.controller;

import dev.coworking.dto.Manager;
import dev.coworking.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManagerController {

    private final ManagerService managerService;

    @PutMapping(value = "manager", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Manager updateManager(@RequestBody Manager changingManager) {
        return managerService.updateManager(changingManager);
    }

    @GetMapping(value = "manager/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Manager getPersInfo(@PathVariable("id") Long id) {
        return managerService.getPersInfo(id);
    }
}
