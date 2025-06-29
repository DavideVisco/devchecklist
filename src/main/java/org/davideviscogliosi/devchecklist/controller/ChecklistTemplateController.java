package org.davideviscogliosi.devchecklist.controller;

import org.davideviscogliosi.devchecklist.controller.handler.ChecklistTemplateHandler;
import org.davideviscogliosi.devchecklist.dto.ChecklistTemplateDTO;
import org.davideviscogliosi.devchecklist.dto.OutcomeDTO;
import org.davideviscogliosi.devchecklist.model.User;
import org.davideviscogliosi.devchecklist.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/templates")
public class ChecklistTemplateController {

    private final ChecklistTemplateHandler checklistTemplateHandler;
    private final UserRepository userRepository;

    public ChecklistTemplateController(ChecklistTemplateHandler checklistTemplateHandler,UserRepository userRepository) {
        this.userRepository = userRepository;
        this.checklistTemplateHandler = checklistTemplateHandler;
    }

    @PostMapping
    public ResponseEntity<OutcomeDTO<ChecklistTemplateDTO>> createTemplate(@RequestBody ChecklistTemplateDTO template, Principal principal) {

        Optional<User> owner = userRepository.findByUsername(principal.getName());
        if(owner.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OutcomeDTO<>("User not found"));
        }

        if(template.getName() == null || template.getName().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OutcomeDTO<>("Template Name is mandatory"));
        }

        return checklistTemplateHandler.createTemplate(template,owner.get());
    }

    @GetMapping
    public ResponseEntity<OutcomeDTO<List<ChecklistTemplateDTO>>> getAllTemplates() {
        return checklistTemplateHandler.getAllTemplates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutcomeDTO<ChecklistTemplateDTO>>  getTemplateById(@PathVariable Long id) {
        return checklistTemplateHandler.getTemplateById(id);
    }
}