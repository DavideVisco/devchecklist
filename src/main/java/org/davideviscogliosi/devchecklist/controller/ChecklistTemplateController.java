package org.davideviscogliosi.devchecklist.controller;

import org.davideviscogliosi.devchecklist.controller.handler.ChecklistTemplateHandler;
import org.davideviscogliosi.devchecklist.controller.handler.ResponseHandler;
import org.davideviscogliosi.devchecklist.dto.ChecklistTemplateDTO;
import org.davideviscogliosi.devchecklist.dto.OutcomeDTO;
import org.davideviscogliosi.devchecklist.model.ChecklistTemplate;
import org.davideviscogliosi.devchecklist.service.ChecklistTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class ChecklistTemplateController {

    private final ChecklistTemplateHandler checklistTemplateHandler;

    public ChecklistTemplateController(ChecklistTemplateHandler checklistTemplateHandler) {
        this.checklistTemplateHandler = checklistTemplateHandler;
    }

    @PostMapping
    public ResponseEntity<OutcomeDTO<ChecklistTemplateDTO>> createTemplate(@RequestBody ChecklistTemplateDTO template) {

        return checklistTemplateHandler.createTemplate(template);
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