package org.davideviscogliosi.devchecklist.controller;


import org.davideviscogliosi.devchecklist.controller.handler.ChecklistRunHandler;
import org.davideviscogliosi.devchecklist.dto.ChecklistRunDTO;
import org.davideviscogliosi.devchecklist.dto.OutcomeDTO;
import org.davideviscogliosi.devchecklist.model.User;
import org.davideviscogliosi.devchecklist.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ChecklistRunController {

    private final ChecklistRunHandler checklistRunHandler;
    private final UserRepository userRepository;

    public ChecklistRunController(ChecklistRunHandler checklistRunHandler,UserRepository userRepository) {
        this.userRepository = userRepository;
        this.checklistRunHandler = checklistRunHandler;
    }

    @PostMapping("/templates/{templateId}/runs")
    public ResponseEntity<OutcomeDTO<ChecklistRunDTO>> createRun(@PathVariable Long templateId, @RequestParam String name, Principal principal) {
        Optional<User> owner = userRepository.findByUsername(principal.getName());
        if(owner.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OutcomeDTO<>("User not found"));
        }

        return checklistRunHandler.createRun(templateId,name,owner.get());
    }

    @GetMapping("/runs/{runId}")
    public ResponseEntity<OutcomeDTO<ChecklistRunDTO>> getRun(@PathVariable Long runId) {
        return checklistRunHandler.getRun(runId);
    }

    @PostMapping("/runs/{runId}/items/{itemId}/toggle")
    public ResponseEntity<OutcomeDTO<ChecklistRunDTO>> toggleItem(@PathVariable Long runId, @PathVariable Long itemId) {
        return checklistRunHandler.toggleItem(runId,itemId);
    }
}
