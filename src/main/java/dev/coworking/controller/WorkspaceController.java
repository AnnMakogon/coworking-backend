package dev.coworking.controller;

import dev.coworking.dto.Workspace;
import dev.coworking.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    // получение рабочих пространств по id manager
    @GetMapping(value = "workspaceManager/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Workspace> getWorkspaces(@PathVariable("id")  Long id){
        return workspaceService.getWorkspaces(id);
    }

    // получение 1 конкретного рп для просмотра и дальнейшей брони
    @GetMapping(value = "workspaceConcrete/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Workspace getConcreteWorkspace(@PathVariable("id") Long id){
        return workspaceService.getConcreteWorkspace(id);
    }

    // получение всех для карты
    @GetMapping(value = "workspace", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Workspace> getAllWorkspace(){
        return workspaceService.getAllWorkspace();
    }

    //апдейт и добавление рп у manager
    @PutMapping(value = "workspace", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateOrAddWorkspace(@RequestBody Workspace newWorkspace){
        workspaceService.updateOrAddWorkspace(newWorkspace);
    }

    @DeleteMapping(value = "workspace/{id}",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteWorkspace(@PathVariable("id") Long id){
        workspaceService.deleteWorkspace(id);
    }

}
