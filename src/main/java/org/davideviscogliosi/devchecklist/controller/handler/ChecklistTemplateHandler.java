package org.davideviscogliosi.devchecklist.controller.handler;

import org.davideviscogliosi.devchecklist.dto.ChecklistTemplateDTO;
import org.davideviscogliosi.devchecklist.dto.OutcomeDTO;
import org.davideviscogliosi.devchecklist.service.ChecklistTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class ChecklistTemplateHandler extends ResponseHandler{

    private final ChecklistTemplateService templateService;

    public ChecklistTemplateHandler(ChecklistTemplateService templateService) {
        this.templateService = templateService;
    }

    public ResponseEntity<OutcomeDTO<ChecklistTemplateDTO>> createTemplate(@RequestBody ChecklistTemplateDTO template) {
        return executeWithResponse("create-template" , () -> templateService.createTemplate(template));
    }

    public ResponseEntity<OutcomeDTO<List<ChecklistTemplateDTO>>> getAllTemplates() {
        return executeWithResponse("get-all-templates" , templateService::getAllTemplates);
    }

    public ResponseEntity<OutcomeDTO<ChecklistTemplateDTO>> getTemplateById(@PathVariable Long id) {
        return executeWithResponse("get-template-by-id" ,() -> templateService.getTemplateById(id));

    }
}

