package dev.coworking.controller;

import dev.coworking.dto.Workspace;
import dev.coworking.dto.WorkspaceCreate;
import dev.coworking.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    @GetMapping(value = "workspaceManager/{id}")
    //todo не секьюрно, менеджер 1 может получить данные менеджера 2 по запросу с другим id, определять текущего менеджера можно по Principal ( в него помимо username можно засунуть id)
    public Page<Workspace> getWorkspacesByManagerId(@PathVariable("id")  Long id){
        return workspaceService.getWorkspaces(id);
    }

    //получение всех рабочих пространств для Customer
    @GetMapping(value = "workspaceCustomer")
    public Page<Workspace> getWorkspaceCustomer(){
        return workspaceService.getWorkspacesCustomer();
    }

    // получение 1 конкретного рп для просмотра и дальнейшей брони
    @GetMapping(value = "workspaceConcrete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Workspace getConcreteWorkspace(@PathVariable("id") Long id){
        return workspaceService.getConcreteWorkspace(id);
    }

    // получение всех для карты
    @GetMapping(value = "workspace", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Workspace> getAllWorkspaces(){
        return workspaceService.getAllWorkspace();
    }

    // апдейт рп у manager
    @PutMapping(value = "workspace", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateWorkspace(@RequestBody Workspace newWorkspace){
        workspaceService.updateWorkspace(newWorkspace);
    }

    // добавление рп у manager
    @PostMapping(value = "newworkspace", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addWorkspace(@RequestBody WorkspaceCreate newWorkspace){
        workspaceService.addWorkspace(newWorkspace);
    }

    @DeleteMapping(value = "workspace/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteWorkspace(@PathVariable("id") Long id){
        workspaceService.deleteWorkspace(id);
    }

}
